package forsyde.io.generators.java

import forsyde.io.generators.utils.Packages
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference

class ClassToJava {
	
	static def toText(EClass cls) {
		'''
		package «cls.EPackage.packageSequence.map[p | p.name].join('.')»;
		
		// General imports
		import java.util.ArrayList;
		import java.util.List;
		import java.util.HashMap;
		import java.util.Map;
		import java.util.Set;
		import java.util.HashSet;
		import java.util.stream.Stream;
		
		«IF cls.name == "ForSyDeIO"»
		// additional imports for the main IO class
		import org.xml.sax.SAXException;
		import java.io.File;
		import java.io.IOException;
		import javax.xml.parsers.DocumentBuilder;
		import javax.xml.parsers.DocumentBuilderFactory;
		import javax.xml.parsers.ParserConfigurationException;
		«ENDIF»
		
		// required import for parsing and writing
		import org.w3c.dom.*;
		
		// imports for other classes
		«FOR i : cls.necessaryImports.filter[e | e != cls.EPackage]»
		import «i.packageSequence.map[name].join('.')».*;
		«ENDFOR»
«««		«FOR i : cls.allSubclasses.map[e | e.EPackage].toSet»
«««		«IF !cls.necessaryImports.filter[e | e != cls.EPackage].contains(i)»
«««		import «i.packageSequence.map[name].join('.')».*;
«««		«ENDIF»
«««		«ENDFOR»
«««		This needs to here for the updatehook feature
		«IF !cls.necessaryImports.map[name].contains("Core") && 
			!cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
		import ForSyDe.Model.Core.*;
		«ENDIF»
		
		/**
		 * This class has been automatically generated from ForSyDe-IO generation routines. Special comments follow whenever pertinent.
		 */
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
			public List<«r.EType.name»> «r.name»;
			«ELSE»
			public «r.EType.name» «r.name»;
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
			
			«IF cls.name == "ForSyDeIO"»
			«mainClassExtraCode(cls)»
			«ENDIF»
			
			// for parsing: remember where to update Ids later
			«IF cls.ESuperTypes.empty»
			protected Map<String, String> attrIdRequests = new HashMap<>();
			«ENDIF»
		
			«parseCode(cls)»
			
			«parseCodeInPlace(cls)»
			
			«updateHook(cls)»
			
			«streamContained(cls)»
			
			«shallowCopy(cls)»
		
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
		val pacIm = Packages.necessaryImports(cls).toSet
		val subIm = getAllSubclasses(cls).map[EPackage].toSet
		pacIm.addAll(subIm)
		return pacIm;
	}
	
	static def parseCodeInPlace(EClass cls)
	'''
	// if the parsing is done in random order rather than document order, this might be wrong!!
	public void parseInPlace(Element elem, Map<String, Identifiable> built) {
		
		«IF !cls.ESuperTypes.empty»
		// if there are super classes, use them
		super.parseInPlace(elem, built);
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
			this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», built);
			if (!contained«r.name».hasAttribute("type"))
				this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», built);
			«ELSEIF r.lowerBound == 0 && r.upperBound == 1»
			// element can be null, just ignore it in such case.
			if (elem.getElementsByTagName("«r.name»").getLength() > 0) {
				Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
				this.«r.name» = «r.EReferenceType.name».parse(contained«r.name», built);
			}
			«ELSE»
			// a list of references
			NodeList contained«r.name» = elem.getElementsByTagName("«r.name»");
			for (int i = 0; i < contained«r.name».getLength(); i++) {
				Node node = contained«r.name».item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element child = (Element) contained«r.name».item(i);
					this.«r.name».add(«r.EReferenceType.name».parse(child, built));
				}
			}
			«ENDIF»
		«ELSE»
			// not contained in XML
			«IF r.upperBound == 1»	
			attrIdRequests.put(elem.getAttribute("«r.name»"), "«r.name»");
«««			if (built.containsKey(refId«r.name»)) {
«««				this.«r.name» = («r.EType.name») built.get(refId«r.name»);
«««			} else if(!refId«r.name».isEmpty()) {
«««				if (!requests.containsKey(refId«r.name»)) {
«««					requests.put(refId«r.name», new HashSet<>());
«««				}
«««				attrIdRequests.put(refId«r.name», "«r.name»");
«««				requests.get(refId«r.name»).add(this);
«««			}
			«ELSE»
			for (String s : elem.getAttribute("«r.name»").split(" ")) {
				attrIdRequests.put(s, "«r.name»");
			}
«««			attrIdRequests.put(refId«r.name», "«r.name»");
«««			for(int i = 0; i < refIds«r.name».length; i++) {
«««				if (built.containsKey(refIds«r.name»[i])) {
«««					this.«r.name».add((«r.EReferenceType.name») built.get(refIds«r.name»[i]));
«««				} else if(!refIds«r.name»[i].isEmpty()) {
«««					if (!requests.containsKey(refIds«r.name»[i])) {
«««						requests.put(refIds«r.name»[i], new HashSet<>());
«««					}
«««					attrIdRequests.put(refIds«r.name»[i], "«r.name»");
«««					requests.get(refIds«r.name»[i]).add(this);
«««				}
«««			}
			«ENDIF»
		«ENDIF»
		«ENDFOR»
	}
	'''
		
	static def parseCode(EClass cls) 
	'''
	static public «cls.name» parse(Element elem, Map<String, Identifiable> built) {
		String trueType = elem.getAttribute("xsi:type");
		«FOR subcls : cls.allSubclasses SEPARATOR ' else'»
		if (trueType != null && trueType.endsWith("«subcls.name»")) {
			// upcast to the right type «subcls.name»
			return «subcls.name».parse(elem, built);
		}
		«ENDFOR»
		«cls.name» obj = new «cls.name»();
		obj.parseInPlace(elem, built);
		«IF !cls.EAllAttributes.map[name].filter[a | a == "identifier"].empty»
		built.put(obj.identifier, obj);
		«ENDIF»
		return obj;
	}
	'''
	
	static def updateHook(EClass cls)
	'''
	public void updateHook(Identifiable obj) {
		if (attrIdRequests.containsKey(obj.identifier)) {
			String att = attrIdRequests.get(obj.identifier);
			switch (att) {
				«FOR r : cls.EReferences.filter[e | e.hasId]»
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
			attrIdRequests.remove(obj.identifier);
		}
	}
	'''
	
	static def streamContained(EClass cls)
	'''
	public Stream<Identifiable> streamContained() {
		«IF cls.EAllAttributes.map[name].contains("identifier")»
		Stream<Identifiable> s = Stream.of(this);
		«ELSE»
		Stream<Identifiable> s = Stream.empty();
		«ENDIF»
		«FOR r : cls.EReferences»
			«IF r.containment»
				«IF r.upperBound == 1»
				if (this.«r.name» != null)
					s = Stream.concat(s, this.«r.name».streamContained());
				«ELSE»
				for («r.EReferenceType.name» d : this.«r.name») {
					s = Stream.concat(s, d.streamContained());
				}
				«ENDIF»
			«ENDIF»
		«ENDFOR»
		return s;
	}
	'''
	
	static def shallowCopy(EClass cls)
	'''
	protected void shallowCopyInPlace(«cls.name» copy) {
		«IF !cls.ESuperTypes.empty»
		super.shallowCopyInPlace(copy);
		«ENDIF»
		«FOR a : cls.EAttributes»
		copy.«a.name» = this.«a.name»;
		«ENDFOR»
		«FOR r : cls.EReferences»
		copy.«r.name» = this.«r.name»;
		«ENDFOR»
	}
	
	public «cls.name» shallowCopy() {
		«cls.name» copy = new «cls.name»();
		shallowCopyInPlace(copy);
		return copy;
	}
	'''
	
	static def containedCopy(EClass cls)
	'''
	protected void containedCopyInPlace(«cls.name» copy) {
		«IF !cls.ESuperTypes.empty»
		super.shallowCopyInPlace(copy);
		«ENDIF»
		«FOR a : cls.EAttributes»
		copy.«a.name» = this.«a.name»;
		«ENDFOR»
		«FOR r : cls.EReferences»
		copy.«r.name» = this.«r.name»;
		«ENDFOR»
	}
	
	public «cls.name» containedCopy() {
		«cls.name» copy = new «cls.name»();
		containedCopyInPlace(copy);
		return copy;
	}
	'''
	
	static def mainClassExtraCode(EClass cls)
	'''
	// interface method for the main IO class
	static public ForSyDeIO parseXML(File file) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		Element rootElement = document.getDocumentElement();
		HashMap<String, Identifiable> built = new HashMap<>();
		// first pass, put everything in memory
		ForSyDeIO forsyde = ForSyDeIO.parse(rootElement, built);
		// make all missing connections
		forsyde.streamContained().forEach(i -> {
			forsyde.streamContained().forEach(j -> {
				i.updateHook(j);
			});
		});
		return forsyde;
	}
	
	/**
	 * This method makes sure that all the definitions in the model are reachable from the main IO class, i.e. this one.
	 */
	public void canonicalize() {
		streamContained()
		.filter(d -> d instanceof Definition)
		.map(d -> (Definition) d)
		.forEach(d -> {
			if (!definitions.contains(d))
				definitions.add(d);
		});
	}
	'''
	
}