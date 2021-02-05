package forsyde.io.generators.python.types

import com.google.common.collect.Iterators
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage

class TypePackageToPython {
	
	static def toText(EPackage pak)
	'''
	"""
	This package has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
	"""
	
	import forsyde.io.python.core as core

	
	«FOR type : getAllSubTypes(pak, "Vertex")»
	«vertexToText(type)»
	
	«ENDFOR»
	«FOR type : getAllSubTypes(pak, "Edge")»
	«edgeToText(type)»
	
	«ENDFOR»
	«vertexFactoryToText(pak)»
	
	«edgeFactoryToText(pak)»
	
«««	«factoryToText(pak)»
	'''
	
	static def vertexToText(EClass cls)
	'''
	class «cls.name»(«cls.ESuperTypes.map[name].map[n | n == "Vertex" ? "core.Vertex" : n].join(', ')»):
	
	    def get_type_name(self) -> str:
	        return "«cls.name»"
	        
	    «FOR r : cls.EReferences.filter[EType.name == "Port"]»
	    def get_port_«r.name»(self) -> core.Port:
	        return self.get_port("«r.name»")

	    «ENDFOR»	
	    «FOR r : cls.EReferences.filter[EType.name == "Port"]»
	    def get_«r.name»(self, model) -> «r.EGenericType.ETypeArguments.head.EClassifier.name»:
	        out_port = self.get_port_«r.name»()
	        for n in model.adj[self]:
	            for (_, edata) in model.edges[self][n]:
	                edge = edata["object"]
	                if edge.source_vertex_port == out_port:
	                    return edge.target_vertex
	        raise AttributeError(f"Port «r.name» of {self.identifier} does not exist.")

	    «ENDFOR»
	    «FOR r : cls.EReferences.filter[EType.name.contains("Property")]»
	    def get_«r.name»(self):
	        try:
	            return self.properties["«r.name»"]
	        except KeyError:
	            raise AttributeError(f"Vertex {self.identifier} has no '«r.name»' property.")

   	    «ENDFOR»
	'''
	
	static def edgeToText(EClass cls)
	'''
	class «cls.name»(«cls.ESuperTypes.map[name].map[n | n == "Edge" ? "core.Edge" : n].join(', ')»):
	
	    def get_type_name(self) -> str:
	        return "«cls.name»"
	'''
	
	static def edgeFactoryToText(EPackage pak)
	'''
	class EdgeFactory:
	    """
	    This class is auto generated.
	    It enables import and export of ForSyDe-IO type models by stringification.
	    """

	    @classmethod
	    def build(
	        cls,
		    source: core.Vertex,
		    target: core.Vertex,
		    type_name: str
		) -> core.Edge:
	        «FOR type : pak.eAllContents
	            		.filter[e | e instanceof EClass].map[e | e as EClass]
	            		.filter[EAllSuperTypes.map[name].contains("Edge")]
	            		.toSet»
	        if type_name == "«type.name»":
	            return «type.name»(
	                source_vertex = source,
	                target_vertex = target
	            )
	        «ENDFOR»
	        raise NotImplementedError(
	            f"The Edge type '{type_name}' is not recognized."
	        )
	'''
	
	static def vertexFactoryToText(EPackage pak)
	'''
	class VertexFactory:
	    """
	    This class is auto generated.
	    It enables import and export of ForSyDe-IO type models by stringification.
	    """
	
	    str_to_classes = {
        «FOR type : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass]
        			.filter[EAllSuperTypes.map[name].contains("Vertex")].toSet SEPARATOR ','»
        «''»    "«type.name»": «type.name»
        «ENDFOR»
	    }
	    
	    @classmethod
	    def get_type_from_name(cls,
	                   type_name: str
	                   ) -> type:
	        if type_name in cls.str_to_classes:
	            return cls.str_to_classes[type_name]
	        raise NotImplementedError(
	           f"The type '{type_name}' is not recognized."
	        )
	
	
	    @classmethod
	    def build(
	        cls,
	        identifier: str,
	        type_name: str,
	        ports = None,
	        properties = None
	     ) -> core.Vertex:
	        vtype = cls.get_type_from_name(type_name)
	        return vtype(
	            identifier=identifier,
	            ports=ports if ports else set(),
	            properties=properties if properties else dict()
	        )
	        raise NotImplementedError(
	           f"The Vertex type '{type_name}' is not recognized."
	        )
«««	        «FOR type : pak.eAllContents
«««	            		.filter[e | e instanceof EClass].map[e | e as EClass]
«««	            		.filter[EAllSuperTypes.map[name].contains("Vertex")]
«««	            		.toSet»
«««	        if type_name == "«type.name»":
«««	            return «type.name»(
«««	                identifier = identifier,
«««	                ports = ports,
«««	                properties = properties
«««	            )
«««	            «ENDFOR»
«««	        if strict:
«««	            raise NotImplementedError(
«««	        	    f"The Vertex type '{type_name}' is not recognized."
«««	        	)
«««	        else:
«««	            return core.Vertex()
	'''
	
	static def factoryToText(EPackage pak)
	'''
	class TypeFactory:
	    """
	    This class is auto generated.
	    It enables import and export of ForSyDe-IO type models by stringification.
	    """
	
	    @classmethod
	    def get_type(cls,
	                   type_name: str,
	                   strict: bool = True
	                   ) -> core.ModelType:
	        str_to_classes = {
            «FOR type : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass]
            			.filter[EAllSuperTypes.map[name].contains("Vertex")].toSet SEPARATOR ','»
            «''»    "«type.name»": «type.name»
	        «ENDFOR»
	        }
	        if type_name in str_to_classes:
	            return str_to_classes[type_name].get_instance()
«««	        «FOR subp : pak.ESubpackages»
«««	        subpackage_type = «subp.name»Factory.build_type(type_name, False)
«««	        if subpackage_type is not None:
«««	            return subpackage_type
«««	        «ENDFOR»
	        if strict:
	            raise NotImplementedError(
	              f"The type '{type_name}' is not recognized."
	            )
	        else:
	            return None
	'''
	
	static def classToText(EClass cls)
	'''
	class «cls.name»Type(core.ModelType):
	    """
	    This class has been automatically generated from forsyde-io.
	    Any modifications therein are likely going to be overwritten.
	    """
	
	    def get_type_name(self):
	        return '«cls.name»'
	
	    «IF !cls.ESuperTypes.isEmpty»
	«''»    # the type has at least one super type 
	    # and therefore the function override is generated.
	    def get_super_types(self):
			«FOR s : cls.ESuperTypes»
	«''»        yield «s.name»Type.get_instance()
	«''»        yield from «s.name»Type.get_instance().get_super_types()
	    	«ENDFOR»
	
	    «ENDIF»
	    «IF !cls.EAllReferences.isEmpty»
	    # the type has at least one refence 
	    # and therefore the function override is generated
	    def get_required_ports(self):
	        «FOR r : cls.EReferences.filter[lowerBound == 1 && upperBound == 1]»
	        yield ('«r.name»', «r.EType.name»Type.get_instance())
	        yield from «r.EType.name»Type.get_instance().get_required_ports()
	        «ENDFOR»

	    «ENDIF»
	    «IF !cls.EAttributes.isEmpty»
	    # the type has at least one default attribute 
	    # and therefore the function override is generated
	    def get_required_properties(self):
	        «FOR a : cls.EAttributes»
	        «IF a.defaultValueLiteral.isNullOrEmpty»
	        yield ('«a.name»', None)
	        «ELSE»
	        yield ('«a.name»', «a.pythonDefaultValueLiteral»)
	        «ENDIF»
	        «ENDFOR»
	        «FOR r : cls.ESuperTypes»
	        yield from «r.name»Type.get_instance().get_required_properties()
	        «ENDFOR»

	    «ENDIF»
	'''
	
	static def String listOfAttributeNames(EClass cls) {
		return cls.EAllAttributes.map["'" + name + "'"].join(', ')
	}
	
	static def String listOfReferenceNames(EClass cls) {
		return cls.EAllReferences.map["'" + name + "'"].join(', ')
	}
	
	static def String pythonDefaultValueLiteral(EAttribute a) {
		switch (a.defaultValueLiteral) {
			case "true": "True"
			case "false": "False"
			default: a.defaultValueLiteral
		}
	}
	
	static def List<EClass> getAllSubTypes(EPackage top, String topName) {
		var open = top.eAllContents
		.filter[e | e instanceof EClass].map[e | e as EClass]
		.filter[name == topName]
		var classes = new ArrayList();
		while (open.hasNext) {
			val current = open.next;
			classes.add(current)
			open = Iterators.concat(open, 
				top.eAllContents
				.filter[e | e instanceof EClass].map[e | e as EClass]
				.filter[ESuperTypes.contains(current)])
		}
		classes.remove(0);
		return classes
	}
	
}
