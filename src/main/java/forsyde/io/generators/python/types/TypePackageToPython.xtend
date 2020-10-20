package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import forsyde.io.generators.utils.Packages

class TypePackageToPython {
	
	static def toText(EPackage pak)
	'''
«««		The types import is a workaround for the python from ... import problem with circular depedencies
«««		https://stackoverflow.com/questions/744373/circular-or-cyclic-imports-in-python
«««	
	import forsyde.io.python.core as core 
	«FOR subp : pak.ESubpackages»
	import forsyde.io.python.«Packages.getPackageSequence(pak).reverse.map[p | p.name.toLowerCase].join(".")».«subp.name.toLowerCase» as «subp.name.toLowerCase»
	«ENDFOR»
	
	«FOR type : pak.EClassifiers.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
	«TypeToPython.toText(type)»
	«ENDFOR»
	
	class «pak.name»Factory:
	    """
	    This class was auto generated to enable import and export of ForSyDe-IO type models
	    """
	
	    @classmethod
	    def build_type(cls, type_name: str, strict: bool = True) -> core.Type:
	        «makeTypeBuilder(pak)»
	        if strict:
	            raise NotImplementedError("The type '{0}' is not implemented and could not be built".format(type_name))
	        else:
	            return None
	'''
	
	static def makeTypeBuilder(EPackage pak)
	'''
	«FOR type : pak.EClassifiers.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
	if type_name == "«type.name»":
	    return «type.name»()
	«ENDFOR»
	«FOR subp : pak.ESubpackages»
	delegated = «subp.name.toLowerCase».«subp.name»Factory.build_type(type_name, False)
	if delegated != None:
	    return delegated
    «ENDFOR»
	'''
}
