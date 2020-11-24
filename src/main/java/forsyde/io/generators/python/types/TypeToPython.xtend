package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EClass

class TypeToPython {
	
	static def toText(EClass cls)
	'''
		@dataclass(eq=True, repr=True, frozen=True)
		class «cls.name»(core.Type):
		    """
		    This class has been automatically generated from forsyde-io.
		    Any modifications therein are likely going to be overwritten.
		    """
		
		    type_name: str = '«cls.name»'
		    required_ports: Set[str] = field(
		        default_factory=lambda: set(
		          «FOR r : cls.EAllAttributes SEPARATOR ','»
		          '«r.name»'
		          «ENDFOR»
		        ),
		        repr=False
		    )
		    required_properties: Set[str] = field(
		        default_factory=lambda: set(
		          «FOR a : cls.EAllAttributes SEPARATOR ','»
		          '«a.name»'
		          «ENDFOR»
		        ),
		        repr=False
		    )
		
		    def get_type_name(self):
		        return self.type_name
		
		    def get_required_ports(self):
		        return self.required_ports
		
		    def get_required_properties(self):
		        return self.required_properties
	'''
	
	static def String listOfAttributeNames(EClass cls) {
		return cls.EAllAttributes.map["'" + name + "'"].join(', ')
	}
	
	static def String listOfReferenceNames(EClass cls) {
		return cls.EAllReferences.map["'" + name + "'"].join(', ')
	}
}
