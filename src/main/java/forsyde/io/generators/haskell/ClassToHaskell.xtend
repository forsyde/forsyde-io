package forsyde.io.generators.haskell

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class ClassToHaskell {
	
	static def toText(EClass cls) {
		switch cls {
			
			case cls.hasSubclasses :
			'''
			class «cls.name» where
			  «cls.featuresNames»
			  
			'''
			 
			default :
			'''
			data «cls.name» = «cls.name» {
			  «cls.featuresNames»
			}
			
			«FOR p : cls.ESuperTypes»
			instance «p.name» «cls.name» where
			  «FOR e : p.EStructuralFeatures»
			  «e.name» = _«cls.name»_«e.name»
			  «ENDFOR»
			«ENDFOR»
			
			'''
		}
	}
	
	
	
	static def getFeaturesNames(EClass cls) {
		val attSingle = cls.EAttributes
			.filter[e | e.upperBound == 1]
			.map[e | e.name + ' :: ' + e.EType.name]
		val attList = cls.EAttributes
			.filter[e | e.upperBound != 1]
			.map[e | e.name + ' :: [' + e.EType.name + ']']
		val inheritAttSingle = cls.EAllAttributes
			.filter[e | !cls.EStructuralFeatures.contains(e)]
			.filter[e | e.upperBound == 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: ' + e.EType.name]
		val inheritAttList = cls.EAllAttributes
			.filter[e | !cls.EStructuralFeatures.contains(e)]
			.filter[e | e.upperBound != 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: [' + e.EType.name + ']']
		val refIdSingle = cls.EReferences
			.filter[e | e.hasId]
			.filter[e | e.upperBound == 1]
			.map[e | e.name + ' :: ' + e.idAttr.EType.name]
		val refIdList = cls.EReferences
			.filter[e | e.hasId]
			.filter[e | e.upperBound != 1]
			.map[e | e.name + ' :: [' + e.idAttr.EType.name + ']']
		val inheritRefIdSingle = cls.EAllReferences
			.filter[e | !cls.EStructuralFeatures.contains(e) && e.hasId]
			.filter[e | e.upperBound == 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: ' + e.idAttr.name]
		val inheritRefIdList = cls.EAllReferences
			.filter[e | !cls.EStructuralFeatures.contains(e) && e.hasId]
			.filter[e | e.upperBound != 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: [' + e.idAttr.name + ']']
		val refNoIdSingle = cls.EReferences
			.filter[e | !e.hasId]
			.filter[e | e.upperBound == 1]
			.map[e | e.name + ' :: ' + e.EType.name]
		val refNoIdList = cls.EReferences
			.filter[e | !e.hasId]
			.filter[e | e.upperBound != 1]
			.map[e | e.name + ' :: [' + e.EType.name + ']']
		val inheritRefNoIdSingle = cls.EAllReferences
			.filter[e | !cls.EStructuralFeatures.contains(e) && !e.hasId]
			.filter[e | e.upperBound == 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: ' + e.EType.name]
		val inheritRefNoIdList = cls.EAllReferences
			.filter[e | !cls.EStructuralFeatures.contains(e) && !e.hasId]
			.filter[e | e.upperBound != 1]
			.map[e | "_" + cls.name + "_" + e.name + ' :: [' + e.EType.name + ']']
		val total = attSingle + attList + refNoIdSingle + refNoIdList + refIdSingle + refIdList +
					inheritAttSingle + inheritAttList + inheritRefNoIdSingle + inheritRefNoIdList + 
					inheritRefIdSingle + inheritRefIdList
		return total.reduce[p1, p2 | p1 + ',\n' + p2]
	}
	
	static def getIdAttr(EClass cls) {
		return cls.EAllAttributes
			.findFirst[e | e.name == 'identifier']
	}
	
	static def getIdAttr(EReference ref) {
		return ref.EReferenceType.idAttr
	}
	
	static def hasId(EReference ref) {
		return ref.EReferenceType.allFeatures.exists[e | e.name == 'identifier']
	}
	
	
	static def Iterable<EStructuralFeature> getAllFeatures(EClass cls) {
		switch cls.ESuperTypes.size() {
			case 0 : return cls.EStructuralFeatures
			default : cls.EStructuralFeatures + cls.ESuperTypes.flatMap[ e | e.getAllFeatures() ] 
		}		
	}
	
	static def getAllSuperTypes(EClass cls) {
		switch cls.ESuperTypes.size() {
			case 0 : return #{cls}
			default : cls.ESuperTypes.flatMap[ e | e.getAllFeatures() ] 
		}
	}
	
	static def Boolean hasSubclasses(EClass cls) {
		var root = cls.EPackage;
		while (root.eContainer !== null) 
			root = root.eContainer as EPackage;
		return root.eAllContents.filter[e | e instanceof EClass]
			.map[e | e as EClass]
			.flatMap[e | e.ESuperTypes.iterator]
			.contains(cls)
	}
}