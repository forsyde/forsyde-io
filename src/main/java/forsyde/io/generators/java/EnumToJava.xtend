package forsyde.io.generators.java

import org.eclipse.emf.ecore.EEnum
import forsyde.io.generators.utils.Packages

class EnumToJava {
	
	static def toText(EEnum enu)
	'''
	package «Packages.getPackageSequence(enu.EPackage).map[p | p.name].join('.')»;
	
	enum «enu.name»  {
		«enu.generateNames»
	}
	'''
	
	static def generateNames(EEnum enu) {
		return enu.ELiterals.join(',\n')
	}
	
}