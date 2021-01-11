package forsyde.io.generators.haskell.types

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EAttribute

class TypePackageToHaskell {
	
	static def toText(EPackage pac)
	'''
		module ForSyDe.IO.Haskell.«pac.packageSequence.map[name].join('.')»
		  (
		    Type ( Unknown
		    «FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
    	«''»         , «cls.name»
    	  	«ENDFOR»
		    )
            «IF pac.eAllContents.exists[e | e instanceof EAttribute]»
		«''»    , getTypeDefaultProperties
«««		    , getTypeDefaultPropertiesValues
		    , getTypeDeducedProperties
            «ENDIF»
		    , makeTypeFromName
		  ) where
		  
		import Data.Dynamic
		
		data Type = Unknown |
		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet SEPARATOR ' |'»
		«''»  «cls.name»
		«ENDFOR»
		  deriving (Show, Read, Eq)
		
		getTypeDefaultProperties :: Type -> [String]
		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
		getTypeDefaultProperties «cls.name» = [«cls.EAllAttributes.map['"' + name + '"'].join(", ")»]
		«ENDFOR»
		getTypeDefaultProperties _ = []
		
«««		getTypeDefaultPropertiesValues :: (Typeable a, Read a, Show a) => Type -> String -> a
«««		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
«««		«FOR att : cls.EAllAttributes»
«««		getTypeDefaultPropertiesValues  «cls.name» "«att.name»" = «haskellizeValue(att.defaultValueLiteral)»
«««		«ENDFOR» 
«««		«ENDFOR»
«««		getTypeDefaultPropertiesValues  t p = error ("Type " ++ (show t) ++ " has no default for " ++ p)
		
		getTypeDeducedProperties :: Type -> [String]
«««		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
«««		getTypeDeducedProperties «cls.name» = [«cls.EAllAttributes.map['"' + name + '"'].join(", ")»]
«««		«ENDFOR»
		getTypeDeducedProperties _ = []
		
		makeTypeFromName :: String -> Type
		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
		makeTypeFromName "«cls.name»" = «cls.name»
		«ENDFOR»
		makeTypeFromName _ = Unknown
		
	'''
	
	static def classAttrToMatchPattern(EClass cls, String name)
	'''(«cls.name» «FOR att : cls.EAllAttributes SEPARATOR ' '»«IF att.name == name»«att.name»«ELSE»_«ENDIF»«ENDFOR»)'''
	
	static def classAttrToSetPattern(EClass cls, String name)
	'''(«cls.name» «FOR att : cls.EAllAttributes SEPARATOR ' '»«IF att.name != name»«att.name»«ELSE»_«ENDIF»«ENDFOR»)'''
	
	static def Iterable<EClassifier> getExports(EPackage pac) {
		return pac.classes + pac.dataTypes
	}
	
	static def String haskellizeType(String typeName) {
		switch (typeName) {
			case "Boolean": return "Bool"
			default: return typeName
		}
	}
	
	static def String haskellizeValue(String value) {
		switch (value) {
			case "false": return "False"
			case "true": return "True"
			default: return value
		}
	}
	
	static def Iterable<EPackage> necessaryImports(EPackage pac) {
		return pac.classes.flatMap[c | c.necessaryImports]
			.reject[p | p == pac].toSet
	}
	
	static def Iterable<EPackage> necessaryImports(EClass cls) {
		return cls.EReferences.map[r | r.EType.EPackage] +
			cls.EAttributes.map[a | a.EType.EPackage]
			.reject[e | e.name == 'type'] //take away 'default' type java package
	}
	
	static def Iterable<EClass> getClasses(EPackage pac) {
		return pac.EClassifiers.filter[c | c instanceof EClass]
			.map[c | c as EClass]
	}
	
	static def Iterable<EDataType> getDataTypes(EPackage pac) {
		return pac.EClassifiers.filter[c | c instanceof EDataType]
			.filter[c | !(c instanceof EEnum)]
			.map[c | c as EDataType]
	}
	
	static def Iterable<EEnum> getEnums(EPackage pac) {
		return pac.EClassifiers.filter[c | c instanceof EEnum]
			.map[c | c as EEnum]
	}
	
	static def List<EPackage> getPackageSequence(EPackage pac) {
		switch pac {
			case pac.eContainer === null : {
				val seq = new ArrayList();
				seq.add(pac)
				return seq
			}
			default : {
				val seq = (pac.eContainer as EPackage).packageSequence
				seq.add(pac)
				return seq
			}
		}
	}
}
