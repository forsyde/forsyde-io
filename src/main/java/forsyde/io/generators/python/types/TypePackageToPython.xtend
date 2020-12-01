package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import forsyde.io.generators.utils.Packages

class TypePackageToPython {
	
	static def toText(EPackage pak)
	'''
	from dataclasses import dataclass, field
	from typing import Set, Dict, Optional, Any

	import forsyde.io.python.core as core
	«FOR subp : pak.ESubpackages»
	from forsyde.io.python.«Packages.getPackageSequence(pak).map[p | p.name.toLowerCase].join(".")».«subp.name.toLowerCase» import «subp.name»Factory
	«ENDFOR»
«««	# export from subpackages all types so that they are visible.
«««	«FOR subp : pak.ESubpackages»
«««	«FOR c : subp.EClassifiers»
«««	from forsyde.io.python.«Packages.getPackageSequence(pak).map[p | p.name.toLowerCase].join(".")».«subp.name.toLowerCase» import «c.name»
«««	«ENDFOR»
«««	«ENDFOR»
	
	
	«FOR type : pak.EClassifiers.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
	«TypeToPython.toText(type)»
	
	
	«ENDFOR»
	class «pak.name»Factory:
	    """
	    This class is auto generated.
	    It enables import and export of ForSyDe-IO type models by stringification.
	    """
	
	    @classmethod
	    def build_type(cls,
	                   type_name: str,
	                   strict: bool = True
	                   ) -> core.Type:
	        str_to_classes = {
            «FOR type : pak.EClassifiers.filter[e | e instanceof EClass].map[e | e as EClass].toSet SEPARATOR ','»
            "«type.name»": «type.name»
	        «ENDFOR»
	        }
	        if type_name in str_to_classes:
	            return str_to_classes[type_name]()
	        «FOR subp : pak.ESubpackages»
	        subpackage_type = «subp.name»Factory.build_type(type_name, False)
	        if subpackage_type is not None:
	            return subpackage_type
	        «ENDFOR»
	        if strict:
	            raise NotImplementedError(
	              f"The type '{type_name}' is not recognized."
	            )
	        else:
	            return None
	'''
	
	static def makeTypeBuilder(EPackage pak)
	'''
	
	
	'''
}
