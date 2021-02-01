package forsyde.io.generators.java.types

import org.eclipse.emf.ecore.EClass
import java.util.HashSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import forsyde.io.generators.utils.Packages

class TypesClassToJava {
	
	static def toText(EClass cls)
	'''
	/**
	 * This class has been automatically generated from ForSyDe-IO generation routines. Special comments follow whenever pertinent.
	 */
	package forsyde.io.java.«cls.EPackage.packageSequence.map[p | p.name.toLowerCase].join('.')»;
	
	import java.util.Optional;
	import java.util.stream.Stream;
	import forsyde.io.java.ModelType;
«««	«FOR i : cls.allReferencesClasses.filter[c | c != cls]»
«««	import forsyde.io.java.«i.EPackage.packageSequence.map[p | p.name.toLowerCase].join('.')».«i.name»;
«««	«ENDFOR»
	
	public class «cls.name» extends ModelType {
	
		static Optional<«cls.name»> instance = Optional.of(new «cls.name»());
	
		static public «cls.name» getInstance() {
			if (instance.isEmpty()) {
				instance = Optional.of(new «cls.name»());
			}
			return instance.get();
		}
	
		@Override
		public String getName() {
			return "«cls.name»";
		};
	
		@Override
		public String toString() {
			return "«cls.name»";
		}
	
		@Override
		public Stream<ModelType> getSuperTypes() {
			return Stream.of(
			«FOR s : cls.ESuperTypes SEPARATOR ','»
				«s.name».getInstance()
			«ENDFOR»
			);
		}
	
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
