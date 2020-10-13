package forsyde.io.generators.java

import org.eclipse.emf.ecore.EClass
import java.util.HashSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import forsyde.io.generators.utils.Packages

class TypesToJava {
	
	static def toText(EClass cls)
	'''
	/**
	 * This class has been automatically generated from ForSyDe-IO generation routines. Special comments follow whenever pertinent.
	 */
	package forsyde.io.java.«cls.EPackage.packageSequence.map[p | p.name.toLowerCase].join('.')»;
	
	import forsyde.io.java.FType;
	«FOR i : cls.allReferencesClasses.filter[c | c != cls]»
	import forsyde.io.java.«i.EPackage.packageSequence.map[p | p.name.toLowerCase].join('.')».«i.name»;
	«ENDFOR»
	
«««	«IF cls.EAllSuperTypes.length > 0»
«««	public class «cls.name» «FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR»
«««		extends «cls.EAllSuperTypes.reverseView.head.name» {
«««	«ELSE»
«««	public class «cls.name»	«FOR tparam : cls.ETypeParameters BEFORE '<' SEPARATOR ', ' AFTER '>'»«tparam.name»«ENDFOR» {
«««	«ENDIF»
	public class «cls.name»	implements FType {
		
		@Override
		public String getName() {
			return "«cls.name»";
		};
	}
	'''
	
	static def Iterable<EClass> getAllReferencesClasses(EClass cls) {
		val refs = new HashSet<EClass>();
		refs.addAll(cls.EReferences.map[r | r.EType as EClass].toSet)
		return refs
	}
	
	static def List<EPackage> getPackageSequence(EPackage pac) {
		return Packages.getPackageSequence(pac)
	}
}