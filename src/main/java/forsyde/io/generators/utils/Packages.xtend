package forsyde.io.generators.utils

import org.eclipse.emf.ecore.EPackage
import java.util.List
import java.util.ArrayList
import org.eclipse.emf.ecore.EClass

class Packages {
	
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
	
	static def Iterable<EPackage> necessaryImports(EClass cls) {
		return cls.EReferences.map[r | r.EType.EPackage] +
			cls.EAttributes.map[a | a.EType.EPackage]
			.reject[e | e.name == 'type'] //take away 'default' type java package
	}
}