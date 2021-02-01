package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier

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
		    required_ports: Dict[str, Optional[str]] = field(
		        default_factory=lambda: {
		          «FOR r : cls.EAllReferences.filter[lowerBound == 1 && upperBound == 1] SEPARATOR ','»
		          '«r.name»': '«r.EType.name»'
		          «ENDFOR»
		        },
		        repr=False
		    )
		    required_properties: Dict[str, Any] = field(
		        default_factory=lambda: {
		          «FOR a : cls.EAllAttributes SEPARATOR ','»
		          «IF a.defaultValueLiteral.isNullOrEmpty»
		          '«a.name»': None
		          «ELSE»
		          '«a.name»': «a.defaultValueLiteral»
		          «ENDIF»
		          «ENDFOR»
		        },
		        repr=False
		    )
		    super_types: List[core.Type] = field(
		        default_factory=lambda: [
		        	«FOR s : cls.ESuperTypes SEPARATOR ','»
		«''»            «s.name»()
		        	«ENDFOR»
		        ],
		        repr=False
		    )
		
		    def get_type_name(self):
		        return self.type_name
		
		    def get_required_ports(self):
		        return self.required_ports
		
		    def get_required_properties(self):
		        return self.required_properties

		    def is_refinement(self, other: core.Type) -> bool:
		        return self == other or any(r.is_refinement(other) for r in self.super_types)
	'''
	
	static def String listOfAttributeNames(EClass cls) {
		return cls.EAllAttributes.map["'" + name + "'"].join(', ')
	}
	
	static def String listOfReferenceNames(EClass cls) {
		return cls.EAllReferences.map["'" + name + "'"].join(', ')
	}
}
