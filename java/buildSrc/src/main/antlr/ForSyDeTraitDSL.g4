grammar ForSyDeTraitDSL;

ID
    :   ('::'|[A-Za-z])('::'|[A-Za-z0-9])*
;

WS : [ \t\r\n]+ -> skip ;


edgeTrait:
	name=ID ('refines' refinedTraits+=ID (',' refinedTraits+=ID)?)?
	('{' '}')?
;


vertexPort:
	name=ID 'is' (modifiers+=ID)* connectedVertexTrait=ID
;

vertexPropertyType:
	typeName='int' |
	typeName='integer' |
	typeName='float' |
	typeName='bool' |
	typeName='boolean' |
	typeName='long' |
	typeName='double' |
	typeName='real' |
	typeName='str' |
	typeName='string' |
	typeName='array' '<' arrayType=vertexPropertyType '>' |
	typeName='intmap' '<' intMapType=vertexPropertyType '>' |
	typeName='integermap' '<' intMapType=vertexPropertyType '>' |
	typeName='intMap' '<' intMapType=vertexPropertyType '>' |
	typeName='integerMap' '<' intMapType=vertexPropertyType '>' |
	typeName='strmap' '<' strMapType=vertexPropertyType '>' |
	typeName='stringmap' '<' strMapType=vertexPropertyType '>' |
	typeName='strMap' '<' strMapType=vertexPropertyType '>' |
	typeName='stringMap' '<' strMapType=vertexPropertyType '>'
;


vertexProperty:
	name=ID 'is' propertyType=vertexPropertyType
;

vertexTrait:
	name=ID
	('refines' refinedTraits+=ID (',' refinedTraits+=ID)?)?
	('{'
	('port' requiredPorts+=vertexPort | 'prop' requiredProperties+=vertexProperty | 'property' requiredProperties+=vertexProperty)*
	'}')?
;

traitHierarchy:
	namespace=ID '{'
	('vertex' vertexTrait | 'edge' edgeTrait | 'namespace' traitHierarchy)*
	'}'
	;

rootTraitHierarchy:
	('vertex' vertexTrait | 'edge' edgeTrait | 'namespace' traitHierarchy)*
;


/*
ArrayVertexPropertyType returns ArrayVertexPropertyType:
	'array' '<' valueTypes= VertexPropertyType '>'
;

IntMapVertexPropertyType returns ArrayVertexPropertyType:
	('intmap' | 'intMap' | 'integermap' | 'integerMap') '<' valueTypes=VertexPropertyType '>'
;


StrMapVertexPropertyType returns ArrayVertexPropertyType:
	('srtmap' | 'strMap' | 'stringmap' | 'stringMap') '<' valueTypes= VertexPropertyType '>'
;

*/
