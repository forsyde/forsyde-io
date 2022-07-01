parser grammar ForSyDeFioDL;

options { tokenVocab=ForSyDeFioDLLex; }

//fragment ALPHABETIC: [a-zA-Z];
//
//NUMERIC: [0-9];
//
//fragment ALPHANUM: [a-zA-Z0-9];
//
//QUALIFIER: '::';
//
//CLARIFIER: ':';
//
//SEPARATOR: ',';
//
//QUOTE: '"';
//
//PORTS_START: '(';
//
//PORTS_END: ')';
//
//LIST_START: '[';
//
//LIST_END: ']';
//
//DICT_START: '{';
//
//DICT_END: '}';
//
//INTEGER: ('-')?NUMERIC+('_i')?;
//LONG: ('-')?NUMERIC+'_l';
//REAL: ('-')?(NUMERIC*'.'NUMERIC+ | NUMERIC+'.'NUMERIC*)'_'NUMERIC+;
//
//QUALIFIED_STRING
//    :   ('::'|ALPHABETIC)('::'|'_'|ALPHANUM)*
//;
//
//INLINE_SYMBOLS: ('*'|'.'|'='|';'|'_'|'+'|'<'|'>'|'-');
//
//WS : [ \t\r\n]+ -> channel(HIDDEN) ;

number: intVal=INTEGER | longVal=LONG | realVal=REAL;

//stringVal:
//    QUOTE  (
//       content+=QUALIFIED_STRING |
//       content+=NUMERIC |
//       content+=INTEGER |
//       content+=LONG |
//       content+=REAL |
//       content+=INLINE_SYMBOLS |
//       content+=PORTS_START |
//       content+=PORTS_END |
//       content+=LIST_START |
//       content+=LIST_END |
//       content+=DICT_START |
//       content+=DICT_END |
//       content+=SEPARATOR  |
//       content+=WS
//   )* QUOTE
//;

systemGraph:
    SYSTEMGRAPH_LIT DICT_START (vertexes+=vertex | edges+=edge)* DICT_END
;

vertex:
    VERTEX_LIT (name=QUALIFIED_STRING | QUOTE_START name=QUOTED_STRING QUOTE_END)
    LIST_START (traits+=QUALIFIED_STRING (SEPARATOR traits+=QUALIFIED_STRING)*)? LIST_END
    PORTS_START (ports+=QUALIFIED_STRING (SEPARATOR ports+=QUALIFIED_STRING)*)? PORTS_END
    DICT_START (QUOTE_START propertyNames+=QUOTED_STRING QUOTE_END CLARIFIER propertyValues+=vertexPropertyValue
        (SEPARATOR QUOTE_START propertyNames+=QUOTED_STRING QUOTE_END CLARIFIER propertyValues+=vertexPropertyValue)*)? DICT_END
;

vertexPropertyValue:
    number |
    booleanValue=BOOLEAN |
    QUOTE_START stringValue=QUOTED_STRING QUOTE_END |
    vertexPropertyArray |
    vertexPropertyMap
;

vertexPropertyArray:
    LIST_START (arrayEntries+=vertexPropertyValue (SEPARATOR arrayEntries+=vertexPropertyValue)*)? LIST_END
;

vertexPropertyMapKey:
    number |
    QUOTE_START stringValue=QUOTED_STRING QUOTE_END
;

vertexPropertyMap:
    DICT_START (mapKey+=vertexPropertyMapKey CLARIFIER mapValue+=vertexPropertyValue (SEPARATOR mapKey+=vertexPropertyMapKey CLARIFIER mapValue+=vertexPropertyValue)*)? DICT_END
;

edge:
    EDGE_LIT
    LIST_START (traits+=QUALIFIED_STRING (SEPARATOR traits+=QUALIFIED_STRING)*)? LIST_END
    FROM_LIT
    (source=QUALIFIED_STRING | QUOTE_START source=QUOTED_STRING QUOTE_END)
    (PORT_LIT (sourceport=QUALIFIED_STRING | QUOTE_START sourceport=QUOTED_STRING QUOTE_END))?
    TO_LIT
    (target=QUALIFIED_STRING | QUOTE_START target=QUOTED_STRING QUOTE_END)
    (PORT_LIT (targetport=QUALIFIED_STRING | QUOTE_START targetport=QUOTED_STRING QUOTE_END))?

;

//edgeTrait:
//	name=QUALIFIED_STRING ('refines' refinedTraits+=QUALIFIED_STRING (',' refinedTraits+=QUALIFIED_STRING)?)?
//	('{'
//        ('source' sourceVertexes+=QUALIFIED_STRING | 'target' targetVertexes+=QUALIFIED_STRING )*
//	 '}')?
//;
//
//
//vertexPort:
//	name=QUALIFIED_STRING 'is' (modifiers+=QUALIFIED_STRING)* connectedVertexTrait=QUALIFIED_STRING ('with' connectingEdgeTrait=QUALIFIED_STRING)?
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
//	name=QUALIFIED_STRING 'is' propertyType=vertexPropertyType
//;
//
//vertexTrait:
//	name=QUALIFIED_STRING
//	('refines' refinedTraits+=QUALIFIED_STRING (',' refinedTraits+=QUALIFIED_STRING)?)?
//	('{'
//	('port' requiredPorts+=vertexPort | 'prop' requiredProperties+=vertexProperty | 'property' requiredProperties+=vertexProperty)*
//	'}')?
//;
//
//traitHierarchy:
//	namespace=QUALIFIED_STRING '{'
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
