package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EClass

class TypeToPython {
	
	static def toText(EClass cls)
	'''
		class «cls.name»(core.Type):
		    """
		    This class has been automatically generated from forsyde-io.
		    Any modifications therein are likely going to be overwritten.
		    """
		
		    def __init__(self):
		        pass
		
		    def __repr__(self):
		        return self.get_type_name()
		
		    def get_type_name(self):
		        return "«cls.name»"
		
		    def get_required_ports(self):
		        «IF cls.EAllReferences.isEmpty»
		        return []
		        «ELSE»
		        return [
		        «FOR r : cls.EAllReferences SEPARATOR ','»
		        «''»  "«r.name»"
		        «ENDFOR»
		        ]
		        «ENDIF»
		
		    def get_required_properties(self):
		        «IF cls.EAllAttributes.isEmpty»
		        return []
		        «ELSE»
		        return [
		        «FOR a : cls.EAllAttributes SEPARATOR ','»
		        «''»  "«a.name»"
		        «ENDFOR»
		        ]
		        «ENDIF»
	'''
}
