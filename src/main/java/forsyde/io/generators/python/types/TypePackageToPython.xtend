package forsyde.io.generators.python.types

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import forsyde.io.generators.utils.Packages
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.EAttribute

class TypePackageToPython {
	
	static def toText(EPackage pak)
	'''
	"""
	This package has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
	"""
	
«««	
«««	from typing import Dict
«««	from typing import Optional
«««	from typing import Any
«««	from typing import List

	import forsyde.io.python.core as core

	
	«FOR type : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
	«classToText(type)»
	
	«ENDFOR»
	«factoryToText(pak)»
	'''
	
	static def factoryToText(EPackage pak)
	'''
	class «pak.name»Factory:
	    """
	    This class is auto generated.
	    It enables import and export of ForSyDe-IO type models by stringification.
	    """
	
	    @classmethod
	    def build_type(cls,
	                   type_name: str,
	                   strict: bool = True
	                   ) -> core.ModelType:
	        str_to_classes = {
            «FOR type : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet SEPARATOR ','»
            «''»    "«type.name»": «type.name»Type
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
	        yield self
			«FOR s : cls.ESuperTypes»
«««	«''»        yield «s.name»Type.get_instance()
	«''»        yield from «s.name»Type.get_istance().get_super_types()
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
	        «FOR a : cls.EAttributes SEPARATOR ','»
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
	
}
