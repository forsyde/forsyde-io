package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EClass

class TypeToPython {
	
	static def toText(EClass cls)
	'''
«««		The core import is a workaround for the python from ... import problem with circular depedencies
«««		https://stackoverflow.com/questions/744373/circular-or-cyclic-imports-in-python
«««
		class «cls.name»(core.Type):
		    """
		    This class has been automatically generated from forsyde-io.
		    Any modifications therein are likely going to be overwritten.
		    """
		
		    def __init__(self):
		        pass
		        
		    def __repr__(self):
		        return self.get_type_name()
		    
		    def get_type_name(self) -> str:
		        return "«cls.name»"
		
		
	'''
}
