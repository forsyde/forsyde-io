package forsyde.io.generators.java.types

import org.eclipse.emf.ecore.EPackage
import forsyde.io.generators.utils.Packages
import java.util.List
import org.eclipse.emf.ecore.EClass

class TypesFactoryGeneratorJava {
	
	static def toText(EPackage pak)
	'''
	/**
	  * This class has been automatically generated from ForSyDe-IO generation routines. Special comments follow whenever pertinent.
	  */
	package forsyde.io.java.«pak.packageSequence.map[p | p.name.toLowerCase].join('.')»;
	
	import java.util.Optional;
	
	import forsyde.io.java.FType;
	«FOR sub : pak.ESubpackages»
	import forsyde.io.java.«sub.packageSequence.map[p | p.name.toLowerCase].join('.')».«sub.name»Factory;
	«ENDFOR»
	
	public class «pak.name»Factory {
		
		static public Optional<FType> getTypeFromName(String name) {
			
			«IF pak.ESubpackages.empty == false»
			Optional<FType> built;
			«ENDIF»
			«FOR cls : pak.EClassifiers.filter[c | c instanceof EClass]»
			if (name.equals("«cls.name»")) {
			    return Optional.of(new «cls.name»());
			}
			«ENDFOR»
			«FOR sub : pak.ESubpackages»
			built = «sub.name»Factory.getTypeFromName(name);
			if(built.isPresent()) {
			    return built;
			}
			«ENDFOR»
			return Optional.empty();
		}
	}
	'''
	
	static def List<EPackage> getPackageSequence(EPackage pac) {
		return Packages.getPackageSequence(pac)
	}
	
	
}