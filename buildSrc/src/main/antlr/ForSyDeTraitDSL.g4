grammar ForSyDeTraitDSL;

QUALIFIED_ID
    :   ('::'|[A-Za-z])('::'|[A-Za-z0-9])*
;

WS : [ \t\r\n]+ -> skip ;



edgeTrait:
	name=QUALIFIED_ID ('refines' refinedTraits+=QUALIFIED_ID (',' refinedTraits+=QUALIFIED_ID)?)?
	('{'
        ('source' sourceVertexes+=QUALIFIED_ID | 'target' targetVertexes+=QUALIFIED_ID )*
	 '}')?
;


vertexPort:
	name=QUALIFIED_ID 'is' (modifiers+=QUALIFIED_ID)* connectedVertexTrait=QUALIFIED_ID
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
	name=QUALIFIED_ID 'is' propertyType=vertexPropertyType
;

vertexTrait:
	name=QUALIFIED_ID
	('refines' refinedTraits+=QUALIFIED_ID (',' refinedTraits+=QUALIFIED_ID)?)?
	('{'
	('port' requiredPorts+=vertexPort | 'prop' requiredProperties+=vertexProperty | 'property' requiredProperties+=vertexProperty)*
	'}')?
;

traitHierarchy:
	namespace=QUALIFIED_ID '{'
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
