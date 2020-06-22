package forsyde.io.generators.java

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClassifier
import java.util.List
import forsyde.io.generators.utils.Packages

class ClassXMISerializerToJava {
	
	static def toText(EPackage root)
	'''
	package ForSyDe.Model.IO;
	
	//additional imports for the main IO class
	import org.xml.sax.SAXException;
	
	import java.io.File;
	import java.io.IOException;
	import java.util.*;
	
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	
	//required import for parsing and writing
	import org.w3c.dom.*;
	
	// forsyde packages;
	«FOR pac : root.eAllContents.filter[e | e instanceof EClassifier].map[e | e as EClassifier].map[e | e.EPackage]
	.filter[e | e.name != "IO"].toSet»
	import «pac.packageSequence.map[p | p.name].join('.')».*;
	«ENDFOR»
	
	public class ForSyDeIOXMIDriver {
		
		protected ForSyDeIO model = null;
		
		public ForSyDeIO parse(String fileName) throws ParserConfigurationException, SAXException, IOException {
			return parse(new File(fileName));
		}
		
		public ForSyDeIO parse(File file) throws ParserConfigurationException, SAXException, IOException {
			if (model == null) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(file);
				model = parseDFS(document);
			}
			return model;
		}
		
		protected ForSyDeIO parseDFS(Document document) {
			ForSyDeIO parsed;
			Element rootElement = document.getDocumentElement();
			HashMap<String, Identifiable> built = new HashMap<>();
			HashMap<Identifiable, List<String>> requestsIds = new HashMap<>();
			HashMap<Identifiable, List<String>> requestsNames = new HashMap<>();
			parsed = parseForSyDeIO(rootElement, built, requestsIds, requestsNames);
			
			// make all missing connections
			for (Identifiable obj : built.values()) {
				«FOR cls : root.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass]
					.filter[c | !c.EAllAttributes.filter[a | a.name == "identifier"].empty].toSet
				SEPARATOR ' else '»
				if (obj instanceof «cls.name») {
					postParse«cls.name»(obj, built, requestsIds, requestsNames);
				}
				«ENDFOR»
			}		
			return parsed;
		}
		
		protected List<Element> getChildrenOfName(Element elem, String name) {
			List<Element> children = new ArrayList<>();
			NodeList contained = elem.getChildNodes();
			for (int i = 0; i < contained.getLength(); i++) {
				Node node = contained.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(name)) {  
					Element child = (Element) contained.item(i);
					children.add(child);
				}
			}
			return children;
		}
		
		«FOR cls : root.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
		«parseClass(cls)»
		
		«IF !cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
		«postParseClass(cls)»
		«ENDIF»
		«ENDFOR»
		
	}
	'''
	
	static def parseClass(EClass cls)
	'''
	protected «cls.name» parse«cls.name»(Element elem, Map<String, Identifiable> built, HashMap<Identifiable, List<String>> requestsIds, HashMap<Identifiable, List<String>> requestsNames) {
		«IF !cls.allSubclasses.empty»
		String trueType = elem.getAttribute("xsi:type");
		«ENDIF»
		«FOR subcls : cls.allSubclasses SEPARATOR ' else'»
		if (trueType != null && trueType.endsWith("«subcls.name»")) {
			// upcast to the right type «subcls.name»
			return parse«subcls.name»(elem, built, requestsIds, requestsNames);
		}
		«ENDFOR»
		
		«cls.name» newObj = new «cls.name»();
		«FOR a : cls.EAllAttributes»
		«IF a.upperBound == -1»
		for (String s : elem.getAttribute("«a.name»").split(" ")) {
			«IF a.EType.name != "String"»
			newObj.«a.name» = «a.typeName».valueOf(elem.getAttribute("«a.name»"));
			«ENDIF»
			newObj.«a.name».add(s);	
		}
		«ELSEIF a.EType.name == "String"»
		newObj.«a.name» = elem.getAttribute("«a.name»");
		«ELSE»
		// assumed this is a enum, so take the value out of it.
		newObj.«a.name» = «a.typeName».valueOf(elem.getAttribute("«a.name»"));
		«ENDIF»
		«ENDFOR»
		
		«IF !cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
		List<String> reqIds = new ArrayList<>();
		List<String> reqNames = new ArrayList<>();
		«ENDIF»
		«FOR r : cls.EReferences»
		«IF r.containment»
			// contained in XML	
			«IF r.lowerBound == 1 && r.upperBound == 1»
			// element cannot be null, raise exception	
			Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
			newObj.«r.name» = parse«r.EReferenceType.name»(contained«r.name», built, requestsIds, requestsNames);
			if (!contained«r.name».hasAttribute("type"))
				newObj.«r.name» = parse«r.EReferenceType.name»(contained«r.name», built, requestsIds, requestsNames);
			«ELSEIF r.lowerBound == 0 && r.upperBound == 1»
			// element can be null, just ignore it in such case.
			if (elem.getElementsByTagName("«r.name»").getLength() > 0) {
				Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
				newObj.«r.name» = parse«r.EReferenceType.name»(contained«r.name», built, requestsIds, requestsNames);
			}
			«ELSE»
			// a list of references
			for (Element child : getChildrenOfName(elem, "«r.name»")) {
				newObj.«r.name».add(parse«r.EReferenceType.name»(child, built, requestsIds, requestsNames));
			} 
			«ENDIF»
		«ELSE»
			// not contained in XML
			«IF r.upperBound == 1»
			reqIds.add(elem.getAttribute("«r.name»"));
			reqNames.add("«r.name»");
			«ELSE»
			for (String s : elem.getAttribute("«r.name»").split(" ")) {
				reqIds.add(s);
				reqNames.add("«r.name»");
			}
			«ENDIF»
		«ENDIF»
		«ENDFOR»
		«IF !cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
		requestsIds.put(newObj, reqIds);
		requestsNames.put(newObj, reqNames);
		
		built.put(newObj.identifier, newObj);
		«ENDIF»
		return newObj;
	}
	
	'''
	
	static def postParseClass(EClass cls)
	'''
	protected void postParse«cls.name»(Identifiable obj, Map<String, Identifiable> built, HashMap<Identifiable, List<String>> requestsIds, HashMap<Identifiable, List<String>> requestsNames) {
		«cls.name» elem = («cls.name») obj;

		«FOR r : cls.EReferences»
		«IF !r.containment»
			// not contained in XML
			«IF r.upperBound == 1»
			int idx«r.name» = requestsNames.get(elem).indexOf("«r.name»");
			String ref«r.name» = requestsIds.get(elem).get(idx«r.name»);
			elem.«r.name» = («r.EReferenceType.name») built.get(ref«r.name»);
			requestsNames.get(elem).remove(idx«r.name»);
			requestsIds.get(elem).remove(idx«r.name»);
			«ELSE»
			int idx«r.name» = requestsNames.get(elem).indexOf("«r.name»");
			while (idx«r.name» > 0) {
				idx«r.name» = requestsNames.get(elem).indexOf("«r.name»");
				String ref«r.name» = requestsIds.get(elem).get(idx«r.name»);
				elem.«r.name».add((«r.EReferenceType.name») built.get(ref«r.name»));
				requestsNames.get(elem).remove(idx«r.name»);
				requestsIds.get(elem).remove(idx«r.name»);
			}
			«ENDIF»
		«ENDIF»
		«ENDFOR»
	}
	
	'''
	
	static def List<EPackage> getPackageSequence(EPackage pac) {
		return Packages.getPackageSequence(pac)
	}
	
	static def getTypeName(EAttribute a) {
		if (a.EGenericType !== null && a.EGenericType.ETypeParameter !== null) {
			return a.EGenericType.ETypeParameter.name.proper;
		} else
			return a.EType.name.proper;
	}
	
	static def String getProper(String name) {
		switch (name) {
			case 'AnySimpleType': return 'Object'
			default: return name			
		}		
	}
	
	static def Iterable<EClass> getAllSubclasses(EClass cls) {
		var pac = cls.EPackage;
		if (pac.ESuperPackage != null) {
			pac = pac.ESuperPackage; 
		}
		return pac.eAllContents.filter[e | e instanceof EClass]
			.map[e | e as EClass]
			.filter[c | cls.isSuperTypeOf(c)]
			.filter[c | c != cls]
			.toSet
	}
	
}