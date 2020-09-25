package forsyde.io.generators.java

import forsyde.io.generators.utils.Packages
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClassifier
import java.util.HashSet

class ClassToJava {
	
	static def toText(EClass cls) {
		'''
		package «cls.EPackage.packageSequence.map[p | p.name].join('.')»;
		
		// General imports
		import java.util.*;
		import java.util.stream.Stream;
		
		// serialization
		import javax.xml.bind.annotation.XmlAttribute;
		import javax.xml.bind.annotation.XmlElement;
		import javax.xml.bind.annotation.XmlElementRef;
		import javax.xml.bind.annotation.XmlElementRefs;
		import javax.xml.bind.annotation.XmlElementWrapper;
		import javax.xml.bind.annotation.XmlRootElement;
		import javax.xml.bind.annotation.XmlAccessType;
		import javax.xml.bind.annotation.XmlAccessorType;
		
		import com.fasterxml.jackson.annotation.JsonIdentityInfo;
		import com.fasterxml.jackson.annotation.JsonSubTypes;
		import com.fasterxml.jackson.annotation.JsonTypeInfo;
		import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
		import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
		import com.fasterxml.jackson.annotation.JsonIdentityReference;
		import com.fasterxml.jackson.annotation.ObjectIdGenerators;
		import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
		import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
		import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
		
		
		«IF cls.name == "ForSyDeIO"»
«««		// additional imports for the main IO class
«««		import org.xml.sax.SAXException;
«««		import java.io.File;
«««		import java.io.IOException;
«««		import javax.xml.parsers.DocumentBuilder;
«««		import javax.xml.parsers.DocumentBuilderFactory;
«««		import javax.xml.parsers.ParserConfigurationException;
«««		
«««		// required import for parsing and writing
«««		import org.w3c.dom.*;
		«ENDIF»
				
		// imports for other classes
		«FOR i : cls.allRequiredClasses.filter[c | c != cls]»
		import «i.EPackage.packageSequence.map[p | p.name].join('.')».«i.name»;
		«ENDFOR»
«««		«FOR i : cls.allSubclasses.map[e | e.EPackage].toSet»
«««		«IF !cls.necessaryImports.filter[e | e != cls.EPackage].contains(i)»
«««		import «i.packageSequence.map[name].join('.')».*;
«««		«ENDIF»
«««		«ENDFOR»
«««		This needs to here for the updateReference feature
«««		«IF !cls.necessaryImports.map[name].contains("Core") && 
«««			!cls.EAllAttributes.filter[e | e.name == "identifier"].empty»
«««		import ForSyDe.Model.Core.*;
«««		«ENDIF»
		
		/**
		 * This class has been automatically generated from ForSyDe-IO generation routines. Special comments follow whenever pertinent.
		 */
		«IF cls.EAllAttributes.exists[att | att.name == "identifier"]»
		@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "identifier")
		«ENDIF»
		@JacksonXmlRootElement(localName = "«cls.name»", namespace = "ForSyDeIO")
		«IF cls.EAllSuperTypes.length > 0»
		public class «cls.name» «FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR»
			extends «cls.EAllSuperTypes.reverseView.head.name» {
		«ELSE»
		public class «cls.name»	«FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR» {
		«ENDIF»
			
			// attributes of the model
			«FOR a : cls.EAttributes»
			@JacksonXmlProperty(localName = "«a.name»", isAttribute = true)
			«IF a.upperBound !== 1»
			public List<«a.typeName»> «a.name»;
			«ELSEIF a.defaultValueLiteral !== null && !a.defaultValueLiteral.empty»
			public «a.typeName» «a.name» = «a.defaultValueLiteral»;
			«ELSE»
			public «a. typeName» «a.name»;
			«ENDIF»
			«ENDFOR»
			
			// references of the model
			«FOR r : cls.EReferences»
			«IF r.containment == false»
			@JsonIdentityReference(alwaysAsId = true)
			«ENDIF»
			@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
			«IF (r.EType as EClass).allSubclasses.isEmpty == false»
			@JsonSubTypes({
				«FOR sub : (r.EType as EClass).allSubclasses SEPARATOR ','»
				@JsonSubTypes.Type(name = "«sub.name»", value = «sub.name».class)
				«ENDFOR»			
			})
«««			@JacksonXmlElementWrapper(localName = "«r.name»", useWrapping = false)
«««			@JacksonXmlProperty(localName = "«r.name»")
			«ENDIF»
			«IF r.upperBound !== 1»
«««			@XmlAttribute(name = "«r.EType.name»")
			public List<«r.EType.name»> «r.name»;
			«ELSE»
«««			@XmlAttribute(name = "«r.EType.name»")
			public «r.EType.name» «r.name»;
			«ENDIF»
			«ENDFOR»
			
			public «cls.name»() {
				«IF !cls.allSubclasses.empty»
				super();
				«ENDIF»
				«FOR a : cls.EAttributes»
				«IF a.upperBound !== 1»
				«a.name» = new ArrayList<>();
				«ELSE»
				«ENDIF»
				«ENDFOR»
				«FOR r : cls.EReferences»
				«IF r.upperBound !== 1»
				«r.name» = new ArrayList<>();
				«ELSE»
				«ENDIF»
				«ENDFOR»
			}
			
			«IF cls.EAllAttributes.exists[att | att.name == "identifier"]»
			public «cls.name»(String identifier) {
				«IF !cls.allSubclasses.empty»
				super();
				«ENDIF»
				this.identifier = identifier;
				«FOR a : cls.EAttributes»
				«IF a.upperBound !== 1»
				«a.name» = new ArrayList<>();
				«ELSE»
				«ENDIF»
				«ENDFOR»
				«FOR r : cls.EReferences»
				«IF r.upperBound !== 1»
				«r.name» = new ArrayList<>();
				«ELSE»
				«ENDIF»
				«ENDFOR»
			}
			«ENDIF»
			
«««			«IF cls.name == "ForSyDeIO"»
«««			«mainClassExtraCode(cls)»
«««			«ENDIF»
			
«««			// for parsing: remember where to update Ids later
«««			«IF cls.ESuperTypes.empty»
«««			protected Map<String, String> attrIdRequests = new HashMap<>();
«««			«ENDIF»
		
«««			«parseCodeXMI(cls)»
«««			
«««			«parseCodeInPlaceXMI(cls)»
«««			
«««			«updateReference(cls)»
			
«««			«streamContained(cls)»
			
«««			«getReferences(cls)»
			
«««			«shallowCopy(cls)»
			
«««			«containedCopy(cls)»

«««			«toBuilderText(cls)»
			
			«toString(cls)»
		
		}
		'''
	}
	
	static def toBuilderText(EClass cls)
	'''
	«IF cls.EAllSuperTypes.length > 0»
	static public class Builder «FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR»
		extends «cls.EAllSuperTypes.reverseView.head.name».Builder {
	«ELSE»
	static public class Builder	«FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR» {
	«ENDIF»
		
		private «cls.name» toBuild;
		
		// attributes of the model
		«FOR a : cls.EAttributes»
		«IF a.upperBound !== 1»
		public Builder set«a.name.toFirstUpper»(List<«a.typeName»> «a.name») {
			toBuild.«a.name» = «a.name»;
			return self();
		}
		
		«ELSE»
		public Builder set«a.name.toFirstUpper»(«a.typeName» «a.name») {
			toBuild.«a.name» = «a.name»;
			return self();
		}
		«ENDIF»
		«ENDFOR»
		
		«FOR r : cls.EReferences»
		«IF r.upperBound !== 1»
		public Builder set«r.name.toFirstUpper»(List<«r.EType.name»> «r.name») {
			toBuild.«r.name» = «r.name»;
			return self();
		}
		
		«ELSE»
		public Builder set«r.name.toFirstUpper»(«r.EType.name» «r.name») {
			toBuild.«r.name» = «r.name»;
			return self();
		}
		«ENDIF»
		«ENDFOR»
		
		«IF cls.EAllSuperTypes.length > 0»
		@Override
		protected  «cls.name».Builder self() {
			return this;
		}
		
		@Override
		«ELSE»
		protected «cls.name».Builder self() {
			return this;
		}

		«ENDIF»
		public «cls.name» build() {
			return toBuild;
		}
		
		 
	}
	
	static public «cls.name».Builder builder() {
		return new Builder();
	}	
	'''
	
	static def allRequiredClasses(EClass cls) {
		val required = new HashSet<EClass>();
		required.addAll(cls.superClassTree);
		required.addAll(cls.allReferencesClasses);
		required.addAll(cls.EReferences.map[r | r.EType as EClass].flatMap[r | r.allSubclasses]);
		return required;
	}
	
	static def Iterable<EClass> getSuperClassTree(EClass cls)  {
		if (cls.ESuperTypes.empty) {
			return Set.of(cls);
		} else {
			val supers = cls.ESuperTypes.flatMap[s | s.superClassTree].toSet
			supers.add(cls);
			return supers;
		}
	}
	
	static def Iterable<EClass> getAllReferencesClasses(EClass cls) {
		val refs = new HashSet<EClass>();
		refs.addAll(cls.EReferences.map[r | r.EType as EClass].toSet)
		return refs
	}
	
	static def getTypeName(EAttribute a) {
		if (a.EGenericType !== null && a.EGenericType.ETypeParameter !== null) {
			return a.EGenericType.ETypeParameter.name.proper;
		} else if (a.EType.instanceTypeName !== null) {
			return a.EType.instanceTypeName;
		} else
			return a.EType.name.proper;
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
	
	static def String getProper(String name) {
		switch (name) {
			case 'AnySimpleType': return 'Object'
			default: return name			
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
	
	static def toString(EClass cls)
	'''
	public String toString() {
		«IF cls.EAllAttributes.exists[e | e.name == 'identifier']»
		return "{«cls.name» : " + identifier + "}";
		«ELSE»
		return "{«cls.name» : " + hashCode() + "}";
		«ENDIF»
	}
	'''
	
	static def parseCodeInPlaceXMI(EClass cls)
	'''
	// if the parsing is done in random order rather than document order, this might be wrong!!
	public void parseInPlaceXMI(Element elem, Map<String, NamedItem> built) {
		
		«IF !cls.ESuperTypes.empty»
		// if there are super classes, use them
		super.parseInPlaceXMI(elem, built);
		«ENDIF»
		
		«FOR a : cls.EAttributes»
		«IF a.upperBound == -1»
		for (String s : elem.getAttribute("«a.name»").split(" ")) {
			«IF a.EType.name != "String"»
			this.«a.name» = «a.typeName».valueOf(elem.getAttribute("«a.name»"));
			«ENDIF»
			this.«a.name».add(s);	
		}
		«ELSEIF a.EType.name == "String"»
		this.«a.name» = elem.getAttribute("«a.name»");
		«ELSE»
		// assumed this is a enum, so take the value out of it.
		this.«a.name» = «a.typeName».valueOf(elem.getAttribute("«a.name»"));
		«ENDIF»
		«ENDFOR»
		
		«FOR r : cls.EReferences»
		«IF r.containment»
			// contained in XML	
			«IF r.lowerBound == 1 && r.upperBound == 1»
			// element cannot be null, raise exception	
			Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
			this.«r.name» = «r.EReferenceType.name».parseXMI(contained«r.name», built);
			if (!contained«r.name».hasAttribute("type"))
				this.«r.name» = «r.EReferenceType.name».parseXMI(contained«r.name», built);
			«ELSEIF r.lowerBound == 0 && r.upperBound == 1»
			// element can be null, just ignore it in such case.
			if (elem.getElementsByTagName("«r.name»").getLength() > 0) {
				Element contained«r.name» = (Element) elem.getElementsByTagName("«r.name»").item(0);			
				this.«r.name» = «r.EReferenceType.name».parseXMI(contained«r.name», built);
			}
			«ELSE»
			// a list of references
			NodeList contained«r.name» = elem.getChildNodes();
			for (int i = 0; i < contained«r.name».getLength(); i++) {
				Node node = contained«r.name».item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName() == "«r.name»") {  
					Element child = (Element) contained«r.name».item(i);
					this.«r.name».add(«r.EReferenceType.name».parseXMI(child, built));
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
		
	static def parseCodeXMI(EClass cls) 
	'''
	static public «cls.name» parseXMI(Element elem, Map<String, NamedItem> built) {
		«IF !cls.allSubclasses.empty»
		String trueType = elem.getAttribute("xsi:type");
		«ENDIF»
		«FOR subcls : cls.allSubclasses SEPARATOR ' else'»
		if (trueType != null && trueType.endsWith("«subcls.name»")) {
			// upcast to the right type «subcls.name»
			return «subcls.name».parseXMI(elem, built);
		}
		«ENDFOR»
		«cls.name» obj = new «cls.name»();
		obj.parseInPlaceXMI(elem, built);
		«IF !cls.EAllAttributes.map[name].filter[a | a == "identifier"].empty»
		built.put(obj.identifier, obj);
		«ENDIF»
		return obj;
	}
	'''
	
	static def updateReference(EClass cls)
	'''
	public void updateReference(NamedItem obj) {
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
	public Stream<NamedItem> streamContained() {
		«IF cls.ESuperTypes.flatMap[t | t.EAllAttributes].exists[a | a.name == "identifier"]»
		Stream<NamedItem> s = super.streamContained();
		«ELSEIF cls.EAllAttributes.map[name].contains("identifier")»
		Stream<NamedItem> s = Stream.of(this);
		«ELSE»
		Stream<NamedItem> s = Stream.empty();
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
	
	static def getReferences(EClass cls)
	'''
	public Stream<NamedItem> getReferences() {
		Stream<NamedItem> s = Stream.empty();
		«FOR r : cls.EReferences»
			«IF !r.containment»
				«IF r.upperBound == 1»
				if (this.«r.name» != null)
					s = Stream.concat(s, Stream.of(this.«r.name»));
				«ELSE»
				s = Stream.concat(s, this.«r.name».stream());
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
		«IF r.upperBound == 1»
		copy.«r.name» = this.«r.name»;
		«ELSE»
		// clone the list but not the objects themselves
		copy.«r.name» = new ArrayList<«r.EReferenceType.name»>(this.«r.name»);
		«ENDIF»
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
		super.containedCopyInPlace(copy);
		«ENDIF»
		«FOR a : cls.EAttributes»
		copy.«a.name» = this.«a.name»;
		«ENDFOR»
		«FOR r : cls.EReferences.filter[r | r.containment == false]»
		«IF r.upperBound == 1»
		copy.«r.name» = this.«r.name»;
		«ELSE»
		// clone the list but not the objects themselves
		copy.«r.name» = new ArrayList<«r.EReferenceType.name»>(this.«r.name»);
		«ENDIF»
		«ENDFOR»
		«FOR r : cls.EReferences.filter[r | r.containment]»
		«IF r.upperBound == 1»
		copy.«r.name» = this.«r.name».containedCopy();
		«ELSE»
		// clone the list contained
		copy.«r.name» = new ArrayList<«r.EReferenceType.name»>();
		for («r.EReferenceType.name» c : «r.name») {
			copy.«r.name».add(c.containedCopy());
		}
		«ENDIF»
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
	static public ForSyDeIO parseXMI(File file) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		Element rootElement = document.getDocumentElement();
		HashMap<String, NamedItem> built = new HashMap<>();
		// first pass, put everything in memory
		ForSyDeIO forsyde = ForSyDeIO.parseXMI(rootElement, built);
		// make all missing connections
		forsyde.streamContained().forEach(i -> {
			forsyde.streamContained().forEach(j -> {
				i.updateReference(j);
			});
		});
		return forsyde;
	}
	
	/**
	 * This method makes sure that all the definitions in the model are reachable from the main IO class, i.e. this one.
	 */
	public void canonicalize() {
		streamContained()
		.filter(d -> d instanceof NamedItem)
		.map(d -> (NamedItem) d)
		.forEach(d -> {
			if (!elements.contains(d))
				elements.add(d);
		});
	}
	'''
	
}