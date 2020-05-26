package forsyde.io.generators.haskell

import org.eclipse.emf.ecore.EEnum

class EnumToHaskell {
	
	static def toText(EEnum enu)
	'''
	data «enu.name» = «enu.generateNames»
	'''
	
	static def generateNames(EEnum enu) {
		return enu.ELiterals.join('|\n')
	}
}