package forsyde.io.generators.java

import forsyde.io.generators.utils.Packages
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import java.util.Collections
import java.util.Set
import com.google.common.collect.Iterables
import java.util.Iterator

class ClassToJava {
	
	static def toText(EClass cls) {
		'''
		package «cls.EPackage.packageSequence.map[p | p.name].join('.')»;
		
		import java.util.ArrayList;
		import java.util.List;
		import java.util.HashMap;
		import java.util.Map;
		import org.w3c.dom.*;
		
		«FOR i : cls.necessaryImports.filter[e | e != cls.EPackage]»
		import «i.packageSequence.map[name].join('.')».*;
		«ENDFOR»
		«FOR i : cls.allSubclasses.map[e | e.EPackage].toSet»
		«IF !cls.necessaryImports.filter[e | e != cls.EPackage].contains(i)»
		import «i.packageSequence.map[name].join('.')».*;
		«ENDIF»
		«ENDFOR»
«««		This needs to here for the updatehook feature
		«IF !cls.necessaryImports.map[name].contains("Core") && 
			!cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
		import ForSyDe.Model.Core.*;
		«ENDIF»
		
		«IF cls.EAllSuperTypes.length > 0»
		public class «cls.name» extends «cls.EAllSuperTypes.reverseView.head.name» {
		«ELSE»
		public class «cls.name» {
		«ENDIF»
			
			// attributes of the model
			«FOR a : cls.EAttributes»
			«IF a.defaultValueLiteral === null»
			public «a.EAttributeType.properName» «a.name»;
			«ELSE»
			public «a.EAttributeType.properName» «a.name» = «a.defaultValueLiteral»;
			«ENDIF»
			«ENDFOR»
			
			// references of the model
			«FOR r : cls.EReferences»
			«IF r.upperBound !== 1»
			List<«r.EType.name»> «r.name»;
			«ELSE»
			«r.EType.name» «r.name»;
			«ENDIF»
			«ENDFOR»
			
			public «cls.name»() {
				«FOR r : cls.EReferences»
				«IF r.upperBound !== 1»
				«r.name» = new ArrayList<>();
				«ELSE»
				«ENDIF»
				«ENDFOR»
			}
		
		
			«parseCode(cls)»
		
		}
		'''
	}
	
	static def getIdAttr(EClass cls) {
		return cls.EAllAttributes
			.findFirst[e | e.name == 'identifier']
	}
	
	static def getIdAttr(EReference ref) {
		return ref.EReferenceType.idAttr
	}
	
	static def Set<EClass> subClassesInPackage(EClass cls) {
		return cls.EPackage.eAllContents.filter[c | c instanceof EClass]
		.map[c | c as EClass]
		.filter[c | cls.isSuperTypeOf(c)]
		.reject[c | c == cls]
		.toSet
	}
	
	static def hasId(EReference ref) {
		return ref.EReferenceType.EAllAttributes.exists[e | e.name == 'identifier']
	}		
	
	static def Boolean needsList(EClass cls) {
		return cls.EAttributes.map[a | a.upperBound]
		.filter[m | m !== -1]
		.empty
	}
	
	static def String getProperName(EDataType dt) {
		switch (dt.name) {
			case 'AnySimpleType': return 'Object'
			default: return dt.name			
		}		
	}
	
	static def List<EPackage> getPackageSequence(EPackage pac) {
		return Packages.getPackageSequence(pac)
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
	
	static def  Iterable<EPackage> necessaryImports(EClass cls) {
		val i = Packages.necessaryImports(cls)
		val s = i.toSet
		if (!cls.ESuperTypes.empty)
			s.add(cls.ESuperTypes.head.EPackage)
		return s;
	}
	
	static def parseCode(EClass cls) 
	'''
	// if the parsing is done in random order rather than document order, this might be wrong!!
	public void parseInPlace(Element elem, Map<String, Map<String, Object>> requests, Map<String, Object> built) {
		
		«IF !cls.ESuperTypes.empty»
		// if there are super classes, use them
		super.parseInPlace(elem, requests, built);
		«ENDIF»
		
		«FOR a : cls.EAttributes»
		«IF a.EType.name == "Integer"»
		this.«a.name» = Integer.valueOf(elem.getAttribute("«a.name»"));
		«ELSEIF a.EType.name == "Boolean"»
		this.«a.name» = Boolean.valueOf(elem.getAttribute("«a.name»"));
		«ELSEIF a.EType.name == "String"»
		this.«a.name» = elem.getAttribute("«a.name»");
		«ELSE»
		// assumed this is a enum, so take the value out of it.
		this.«a.name» = «a.EType.name».valueOf(elem.getAttribute("«a.name»"));
		«ENDIF»
		«ENDFOR»
		
		«FOR r : cls.EReferences»
		«IF r.containment»
			// contained in XML	
			«IF r.lowerBound == 1 && r.upperBound == 1»
			// element cannot be null, raise exception	
			Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
			this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», requests, built);
			if (!contained«r.name».hasAttribute("type"))
				this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», requests, built);
			«ELSEIF r.lowerBound == 0 && r.upperBound == 1»
			// element can be null, just ignore it in such case.
			if (elem.getElementsByTagName("«r.name»").getLength() > 0) {
				Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
				this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», requests, built);
			}
			«ELSE»
			// a list of references
			NodeList contained«r.name» = elem.getElementsByTagName("«r.name»");
			for (int i = 0; i < contained«r.name».getLength(); i++) {
				Node node = contained«r.name».item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element child = (Element) contained«r.name».item(i);
					this.«r.name».add(«r.EReferenceType.name».parse(child, requests, built));
				}
			}
			«ENDIF»
		«ELSE»
			// not contained in XML
			«IF r.upperBound == 1»	
			String refId«r.name» = elem.getAttribute("«r.name»");
			if (built.containsKey(refId«r.name»)) {
				this.«r.name» = («r.EType.name») built.get(refId«r.name»);
			} else if(!refId«r.name».isEmpty()) {
				if (!requests.containsKey(refId«r.name»)) {
					requests.put(refId«r.name», new HashMap<>());
				}
				requests.get(refId«r.name»).put("«r.name»", this);
			}
			«ELSE»
			String[] refIds«r.name» = elem.getAttribute("«r.name»").split(" ");
			for(int i = 0; i < refIds«r.name».length; i++) {
				if (built.containsKey(refIds«r.name»[i])) {
					this.«r.name».add((«r.EReferenceType.name») built.get(refIds«r.name»[i]));
				} else if(!refIds«r.name»[i].isEmpty()) {
					if (!requests.containsKey(refIds«r.name»[i])) {
						requests.put(refIds«r.name»[i], new HashMap<>());
					}
					requests.get(refIds«r.name»[i]).put("«r.name»", this);
				}
			}
			«ENDIF»
		«ENDIF»
		«ENDFOR»
	}
	
	static public «cls.name» parse(Element elem, Map<String, Map<String, Object>> requests, Map<String, Object> built) {
		String trueType = elem.getAttribute("xsi:type");
		«FOR subcls : cls.allSubclasses SEPARATOR ' else'»
		if (trueType != null && trueType.endsWith("«subcls.name»")) {
			// upcast to the right type «subcls.name»
			return «subcls.name».parse(elem, requests, built);
		}
		«ENDFOR»
		«cls.name» obj = new «cls.name»();
		obj.parseInPlace(elem, requests, built);
		«IF !cls.EAllAttributes.map[name].filter[a | a == "identifier"].empty»
		built.put(obj.identifier, obj);
		if (requests.containsKey(obj.identifier)) {
			Map<String, Object> atts = requests.get(obj.identifier);
			for(String attName : atts.keySet()) {
				Identifiable requester = (Identifiable) atts.get(attName);
				requester.updateHook(attName, obj);
			}
			requests.get(obj.identifier).clear();
			requests.remove(obj.identifier);
		}
		«ENDIF»
		return obj;
	}
	
	public void updateHook(String att, Object obj) {
		switch (att) {
			«FOR r : cls.EReferences»
			case "«r.name»":
				«IF r.upperBound == 1»
				// element cannot be null, raise exception
				this.«r.name» = («r.EReferenceType.name») obj;			
				«ELSE»
				this.«r.name».add((«r.EReferenceType.name») obj);
				«ENDIF»
				break;
			«ENDFOR»
		}
	}
	'''
	
}