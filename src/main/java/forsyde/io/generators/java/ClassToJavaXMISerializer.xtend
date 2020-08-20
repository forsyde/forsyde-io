package forsyde.io.generators.java

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClassifier
import java.util.List
import forsyde.io.generators.utils.Packages

class ClassToJavaXMISerializer {
	
	static def toText(EPackage root)
	'''
	package ForSyDe.Model.IO;
	
	//additional imports for the main IO class
	import org.xml.sax.SAXException;
	
	import java.io.File;
	import java.io.IOException;
	import java.util.*;
	import java.util.stream.Collectors;
	
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.transform.OutputKeys;
	import javax.xml.transform.Transformer;
	import javax.xml.transform.TransformerException;
	import javax.xml.transform.TransformerFactory;
	import javax.xml.transform.dom.DOMSource;
	import javax.xml.transform.stream.StreamResult;
	
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
		
		public void dump(ForSyDeIO forsyde, String fileName) throws ParserConfigurationException, SAXException, IOException, TransformerException {
			dump(forsyde, new File(fileName));
		}
		
		public void dump(ForSyDeIO forsyde, File file) throws ParserConfigurationException, SAXException, IOException, TransformerException {
			model = forsyde;
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element root = dumpForSyDeIO(forsyde, "ForSyDe:ForSyDeIO", document);
			document.appendChild(root);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource domSource = new DOMSource(document);
	        StreamResult streamResult = new StreamResult(file);
	        transformer.transform(domSource, streamResult);
		}
		
		protected ForSyDeIO parseDFS(Document document) {
			ForSyDeIO parsed;
			Element rootElement = document.getDocumentElement();
			HashMap<String, NamedItem> built = new HashMap<>();
			HashMap<NamedItem, List<String>> requestsIds = new HashMap<>();
			HashMap<NamedItem, List<String>> requestsNames = new HashMap<>();
			parsed = parseForSyDeIO(rootElement, built, requestsIds, requestsNames);
			
			// make all missing connections
			for (NamedItem obj : built.values()) {
«««				«FOR cls : root.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass]
«««					.filter[c | !c.EAllAttributes.exists[a | a.name == "identifier"]].toSet
«««				SEPARATOR ' else ' AFTER ' else '»
«««				if (obj instanceof «cls.name») {
«««					postParse«cls.name»(obj, built, requestsIds, requestsNames);
«««				}
«««				«ENDFOR»
				postParseNamedItem(obj, built, requestsIds, requestsNames);
			}
			return parsed;
		}

		
		protected List<Element> getChildrenOfName(Element elem, String name) {
			List<Element> children = new ArrayList<>();
			NodeList contained = elem.getChildNodes();
			for (int i = 0; i < contained.getLength(); i++) {
				Node node = contained.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName() == name) {  
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
		
		«dumpClass(cls)»
		
		«ENDFOR»
	}
	'''
	
	static def parseClass(EClass cls)
	'''
	protected «cls.name» parse«cls.name»(Element elem, Map<String, NamedItem> built, HashMap<NamedItem, List<String>> requestsIds, HashMap<NamedItem, List<String>> requestsNames) {
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
		«FOR r : cls.EAllReferences»
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
	protected void postParse«cls.name»(NamedItem obj, Map<String, NamedItem> built, HashMap<NamedItem, List<String>> requestsIds, HashMap<NamedItem, List<String>> requestsNames) {
		
		«FOR c : cls.allSubclasses SEPARATOR ' else ' AFTER ' else '»
		if (obj instanceof «c.name») {
			postParse«c.name»((«c.name») obj, built, requestsIds, requestsNames);
		}
		«ENDFOR»
		// these braces are harmless. They appear here as a by product of automatic code generation.
		{
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
	}
	
	'''
	
	static def dumpClass(EClass cls)
	'''
	protected Element dump«cls.name»(«cls.name» obj, String name, Document document) {
		
		«FOR c : cls.allSubclasses SEPARATOR ' else '»
		if (obj instanceof «c.name») {
			return dump«c.name»((«c.name») obj, name, document);
		}
		«ENDFOR»
		
		Element current = document.createElementNS("https://forsyde.github.io/ForSyDe", name);
		
		«FOR a : cls.EAllAttributes»
			«IF a.upperBound == 1 && a.lowerBound == 1»
			current.setAttribute("«a.name»", obj.«a.name».toString());
			«ELSEIF a.upperBound == 1 && a.lowerBound == 0»
			if (obj.«a.name» != null)
				current.setAttribute("«a.name»", obj.«a.name».toString());
			«ELSE»
			current.setAttribute("«a.name»", obj.«a.name».stream().map(e -> e.toString()).collect(Collectors.joining(" ")));
			«ENDIF»
		«ENDFOR»
		
		«FOR r : cls.EAllReferences»
			«IF r.containment»
				«IF r.upperBound == 1 && r.lowerBound == 1»
				Element child«r.name» = dump«r.EReferenceType.name»(obj.«r.name», "«r.name»", document);
				current.appendChild(child«r.name»);
				«ELSEIF r.upperBound == 1 && r.lowerBound == 0»
				if (obj.«r.name» != null) {
					Element child«r.name» = dump«r.EReferenceType.name»(obj.«r.name», "«r.name»", document);
					current.appendChild(child«r.name»);
				}
				«ELSE»
				for («r.EReferenceType.name» c : obj.«r.name») {
					Element child = dump«r.EReferenceType.name»(c, "«r.name»", document);
					current.appendChild(child);
				}
				«ENDIF»
			«ELSE»
				«IF r.upperBound == 1 && r.lowerBound == 1»
				current.setAttribute("«r.name»", obj.«r.name».identifier.toString());
				«ELSEIF r.upperBound == 1 && r.lowerBound == 0»
				if (obj.«r.name» != null)
					current.setAttribute("«r.name»", obj.«r.name».identifier.toString());
				«ELSE»
				current.setAttribute("«r.name»", obj.«r.name».stream().map(e -> e.identifier.toString()).collect(Collectors.joining(" ")));
				«ENDIF»
			«ENDIF»
		«ENDFOR»

		current.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type", "«cls.name»");
		
		return current;
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