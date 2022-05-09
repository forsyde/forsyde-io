grammar ForSyDeFioDL;

ALPHABETIC: [a-zA-Z];

NUMERIC: [0-9];

ALPHANUM: [a-zA-Z0-9];

QUALIFIER: '::';

CLARIFIER: ':';

SEPARATOR: ',';

QUOTE: '"';

PORTS_START: '(';

PORTS_END: ')';

LIST_START: '[';

LIST_END: ']';

DICT_START: '{';

DICT_END: '}';

INTEGER: ('-')?NUMERIC+('_i')?;
LONG: ('-')?NUMERIC+'_l';
REAL: ('-')?(NUMERIC*'.'NUMERIC+ | NUMERIC+'.'NUMERIC*)'_'NUMERIC+;

QUALIFIED_ID
    :   ('::'|ALPHABETIC)('::'|'_'|ALPHANUM)*
;

INLINE_SYMBOLS: ('*'|'.'|'='|';'|'_'|'+'|'<'|'>'|'-');

WS : [ \t\r\n]+ -> channel(HIDDEN) ;

number: intVal=INTEGER | longVal=LONG | realVal=REAL;

stringVal:
    QUOTE  (
       content+=QUALIFIED_ID |
       content+=NUMERIC |
       content+=INTEGER |
       content+=LONG |
       content+=REAL |
       content+=INLINE_SYMBOLS |
       content+=PORTS_START |
       content+=PORTS_END |
       content+=LIST_START |
       content+=LIST_END |
       content+=DICT_START |
       content+=DICT_END |
       content+=SEPARATOR  |
       content+=WS
   )* QUOTE
;

systemGraph:
    'systemgraph' DICT_START (vertexes+=vertex | edges+=edge)* DICT_END
;

vertex:
    'vertex' name=QUALIFIED_ID
    LIST_START (traits+=QUALIFIED_ID (SEPARATOR traits+=QUALIFIED_ID)*)? LIST_END
    PORTS_START (ports+=QUALIFIED_ID (SEPARATOR ports+=QUALIFIED_ID)*)? PORTS_END
    DICT_START (propertyNames+=stringVal CLARIFIER propertyValues+=vertexPropertyValue
        (SEPARATOR propertyNames+=stringVal CLARIFIER propertyValues+=vertexPropertyValue)*)? DICT_END
;

vertexPropertyValue:
    number |
    stringVal |
    vertexPropertyArray |
    vertexPropertyMap
;

vertexPropertyArray:
    '[' (arrayEntries+=vertexPropertyValue (SEPARATOR arrayEntries+=vertexPropertyValue)*)? ']'
;

vertexPropertyMapKey:
    number |
    stringVal
;

vertexPropertyMap:
    DICT_START (mapKey+=vertexPropertyMapKey CLARIFIER mapValue+=vertexPropertyValue (SEPARATOR mapKey+=vertexPropertyMapKey CLARIFIER mapValue+=vertexPropertyValue)*)? DICT_END
;

edge:
    'edge'
    '[' (traits+=QUALIFIED_ID (SEPARATOR traits+=QUALIFIED_ID)*)? ']'
    'from' source=QUALIFIED_ID ('port' sourceport=QUALIFIED_ID)? 'to' target=QUALIFIED_ID ('port' targetport=QUALIFIED_ID)?

;

//edgeTrait:
//	name=QUALIFIED_ID ('refines' refinedTraits+=QUALIFIED_ID (',' refinedTraits+=QUALIFIED_ID)?)?
//	('{'
//        ('source' sourceVertexes+=QUALIFIED_ID | 'target' targetVertexes+=QUALIFIED_ID )*
//	 '}')?
//;
//
//
//vertexPort:
//	name=QUALIFIED_ID 'is' (modifiers+=QUALIFIED_ID)* connectedVertexTrait=QUALIFIED_ID ('with' connectingEdgeTrait=QUALIFIED_ID)?
//;
//
//vertexPropertyType:
//	typeName='int' |
//	typeName='integer' |
//	typeName='float' |
//	typeName='bool' |
//	typeName='boolean' |
//	typeName='long' |
//	typeName='double' |
//	typeName='real' |
//	typeName='str' |
//	typeName='string' |
//	typeName='array' '<' arrayType=vertexPropertyType '>' |
//	typeName='intmap' '<' intMapType=vertexPropertyType '>' |
//	typeName='integermap' '<' intMapType=vertexPropertyType '>' |
//	typeName='intMap' '<' intMapType=vertexPropertyType '>' |
//	typeName='integerMap' '<' intMapType=vertexPropertyType '>' |
//	typeName='strmap' '<' strMapType=vertexPropertyType '>' |
//	typeName='stringmap' '<' strMapType=vertexPropertyType '>' |
//	typeName='strMap' '<' strMapType=vertexPropertyType '>' |
//	typeName='stringMap' '<' strMapType=vertexPropertyType '>'
//;
//
//
//vertexProperty:
//	name=QUALIFIED_ID 'is' propertyType=vertexPropertyType
//;
//
//vertexTrait:
//	name=QUALIFIED_ID
//	('refines' refinedTraits+=QUALIFIED_ID (',' refinedTraits+=QUALIFIED_ID)?)?
//	('{'
//	('port' requiredPorts+=vertexPort | 'prop' requiredProperties+=vertexProperty | 'property' requiredProperties+=vertexProperty)*
//	'}')?
//;
//
//traitHierarchy:
//	namespace=QUALIFIED_ID '{'
//	('vertex' vertexTrait | 'edge' edgeTrait | 'namespace' traitHierarchy)*
//	'}'
//	;
//
//rootTraitHierarchy:
//	('vertex' vertexTrait | 'edge' edgeTrait | 'namespace' traitHierarchy)*
//;
//
//
///*
//ArrayVertexPropertyType returns ArrayVertexPropertyType:
//	'array' '<' valueTypes= VertexPropertyType '>'
//;
//
//IntMapVertexPropertyType returns ArrayVertexPropertyType:
//	('intmap' | 'intMap' | 'integermap' | 'integerMap') '<' valueTypes=VertexPropertyType '>'
//;
//
//
//StrMapVertexPropertyType returns ArrayVertexPropertyType:
//	('srtmap' | 'strMap' | 'stringmap' | 'stringMap') '<' valueTypes= VertexPropertyType '>'
//;
//
//*/
