package forsyde.io.generators.prolog.types

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass

class TypesPackageToProlog {
	
	static def toText(EPackage pak)
	'''
	:- module('std_types', [is_type/2]).
	
	«FOR t : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toIterable»
	allowed_type('«t.name»').
	«ENDFOR»
	allowed_type('Unknown').
	
	«FOR t : pak.eAllContents.filter[e | e instanceof EClass].map[e | e as EClass].toIterable»
	type_refines('«t.name»', '«t.name»').
	«FOR s : t.ESuperTypes»
	type_refines('«t.name»', '«s.name»').
	«ENDFOR»
	«ENDFOR»
	
	is_type(T, N) :- T = N, allowed_type(N).
	is_type(T, N) :- allowed_type(T), 
	                 allowed_type(N), 
	                 allowed_type(T2), 
	                 type_refines(T, T2),
	                 type_refines(T2, N).
	'''
	
}