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
		
		«FOR i : cls.necessaryImports»
		import «i.packageSequence.map[name].join('.')».*;
		«ENDFOR»
		
		«IF cls.EAllSuperTypes.length > 0»
		public class «cls.name» extends «cls.EAllSuperTypes.reverseView.head.name» {
		«ELSE»
		public class «cls.name» {
		«ENDIF»
			
			// attributes of the model
			«FOR a : cls.EAttributes»
			«IF a.defaultValueLiteral === null»
			«a.EAttributeType.properName» «a.name»;
			«ELSE»
			«a.EAttributeType.properName» «a.name» = «a.defaultValueLiteral»;
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
	public void parseInPlace(Element elem, Map<String, Object> elemMap) {
		
		«IF !cls.ESuperTypes.empty»
		// if there are super classes, use them
		super.parseInPlace(elem, elemMap);
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
			«FOR subcls : r.EReferenceType.subClassesInPackage»
			if (contained«r.name».getAttribute("type").contains("«subcls.name»"))
				this.«r.name» = «subcls.name».parse(contained«r.name», elemMap);
			«ENDFOR»
			if (!contained«r.name».hasAttribute("type"))
				this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», elemMap);
			«ELSEIF r.lowerBound == 0 && r.upperBound == 1»
			// element can be null, just ignore it in such case.
			if (elem.getElementsByTagName("«r.name»").getLength() > 0) {
				Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
				«FOR subcls : r.EReferenceType.subClassesInPackage»
				if (contained«r.name».getAttribute("type").contains("«subcls.name»"))
					this.«r.name» = «subcls.name».parse(contained«r.name», elemMap);
				«ENDFOR»
				if (!contained«r.name».hasAttribute("type"))
					this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», elemMap);
			}
			«ELSE»
			// a list of references
			NodeList contained«r.name» = elem.getElementsByTagName("«r.name»");
			for (int i = 0; i < contained«r.name».getLength(); i++) {
				Node node = contained«r.name».item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element child = (Element) contained«r.name».item(i);
					«FOR subcls : r.EReferenceType.subClassesInPackage»
					if (child.getAttribute("type").contains("«subcls.name»"))
						this.«r.name».add(«subcls.name».parse(child, elemMap));
					«ENDFOR»
					if (!child.hasAttribute("type"))
						this.«r.name».add(«r.EReferenceType.name».parse(child, elemMap));
				}
			}
			«ENDIF»
		«ELSE»
			// not contained in XML
			«IF r.upperBound == 1»	
			String refId«r.name» = elem.getAttribute("«r.name»");
			if (elemMap.containsKey(refId«r.name»)) {
				«FOR subcls : r.EReferenceType.subClassesInPackage»
				if (elemMap.get(refId«r.name») instanceof «subcls.name»)
					this.«r.name» = («subcls.name») elemMap.get(refId«r.name»);
				«ENDFOR»
				else
					this.«r.name» = («r.EType.name») elemMap.get(refId«r.name»);
			} else {
				this.«r.name» = new «r.EType.name»();
				elemMap.put(refId«r.name», this.«r.name»);
			}
			«ELSE»
			String[] refIds«r.name» = elem.getAttribute("«r.name»").split(" ");
			for(int i = 0; i < refIds«r.name».length; i++) {
				if (elemMap.containsKey(refIds«r.name»[i])) {
					«FOR subcls : r.EReferenceType.subClassesInPackage»
					if (elemMap.get(refId«r.name») instanceof «subcls.name»)
						this.«r.name».add((«subcls.name») elemMap.get(refIds«r.name»[i]));
					«ENDFOR»
					else
						this.«r.name».add((«r.EType.name») elemMap.get(refIds«r.name»[i]));
				} else {
					«r.EType.name» obj = new «r.EType.name»();
					this.«r.name».add(obj);
					elemMap.put(refIds«r.name»[i], obj);
				}
			}
			«ENDIF»
		«ENDIF»
		«ENDFOR»
	}
	
	static public «cls.name» parse(Element elem, Map<String, Object> elemMap) {
		«cls.name» obj;
		«IF !cls.EAllAttributes.map[name].filter[a | a == "identifier"].empty»
		String ident = elem.getAttribute("identifier");
		if (elemMap.containsKey(ident)) {
			obj = («cls.name») elemMap.get(ident);
		} else {
			obj = new «cls.name»();
			elemMap.put(ident, obj);	
		}
		«ELSE»
		obj = new «cls.name»();
		«ENDIF»
		obj.parseInPlace(elem, elemMap);
		return obj;
	}
	'''
	
	
}