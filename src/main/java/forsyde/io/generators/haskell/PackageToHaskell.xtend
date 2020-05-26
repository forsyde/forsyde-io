package forsyde.io.generators.haskell

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EPackage

class PackageToHaskell {
	
	static def toText(EPackage pac)
	'''
	module «pac.packageSequence.map[name].join('.')» where (
	  «pac.exports.map[name].join(',\n')»
	) where
	
	«FOR i : pac.necessaryImports»
	import «i.packageSequence.map[name].join('.')»
	«ENDFOR»
	
	«FOR t : pac.dataTypes»
	type «t.name» = «t.instanceTypeName»
	«ENDFOR»
	
	«FOR e : pac.enums»
	«EnumToHaskell.toText(e)»
	«ENDFOR»
	
	«FOR c : pac.classes»
	«ClassToHaskell.toText(c)»
	«ENDFOR»
	'''
	
	static def Iterable<EClassifier> getExports(EPackage pac) {
		return pac.classes + pac.dataTypes
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