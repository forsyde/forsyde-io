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
		    Type (
		    «FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet SEPARATOR ','»
    	    «''»      «cls.name»
    	  	«ENDFOR»
		    )«IF pac.eAllContents.exists[e | e instanceof EAttribute]»,«ENDIF»
			«FOR att : pac.eAllContents.filter[e | e instanceof EClass]
				.flatMap[e | (e as EClass).EAllAttributes.iterator].toSet SEPARATOR ','»
		    «''»    get«att.EContainingClass.name»«att.name.toFirstUpper»
		  	«ENDFOR»
		  ) where
		
		data Type = Unknown |
		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet SEPARATOR ' |'»
		«''»  «cls.name» «FOR att : cls.EAllAttributes SEPARATOR ' ' AFTER ' '»«haskellizeType(att.EType.name)»«ENDFOR»
		«ENDFOR»
		  deriving (Show, Eq)
		
		«FOR cls : pac.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toSet»
		«FOR att : cls.EAllAttributes»
		get«cls.name»«att.name.toFirstUpper» :: Type -> «haskellizeType(att.EType.name)»
		get«cls.name»«att.name.toFirstUpper» «filterClassAttrToPattern(cls, att.name)» = «att.name»
		get«cls.name»«att.name.toFirstUpper» _ = error "Type element has no attribute '«att.name»'"
		
		«ENDFOR»
		«ENDFOR»
	'''
	
	static def filterClassAttrToPattern(EClass cls, String name)
	'''(«cls.name» «FOR att : cls.EAllAttributes SEPARATOR ' '»«IF att.name == name»«att.name»«ELSE»_«ENDIF»«ENDFOR»)'''
	
	static def Iterable<EClassifier> getExports(EPackage pac) {
		return pac.classes + pac.dataTypes
	}
	
	static def String haskellizeType(String typeName) {
		switch (typeName) {
			case "Boolean": return "Bool"
			default: return typeName
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
