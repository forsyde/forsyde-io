package forsyde.io.generators.sql.types

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass

class TypesPackageToSQL {
	
	static def toText(EPackage pak)
	'''
	«FOR t : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toIterable»
	INSERT OR IGNORE INTO allowed_types (type_name) VALUES ('«t.name»');
	«ENDFOR»
	INSERT OR IGNORE INTO allowed_types (type_name) VALUES ('Unkown');
	
	«FOR t : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toIterable»
	INSERT OR IGNORE INTO super_types_base (type_name, super_type_name) VALUES ('«t.name»', '«t.name»');
	«FOR s : t.ESuperTypes»
	INSERT OR IGNORE INTO super_types_base (type_name, super_type_name) VALUES ('«t.name»', '«s.name»');
	«ENDFOR»
	«ENDFOR»
	'''
	
}