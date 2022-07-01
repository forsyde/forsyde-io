lexer grammar ForSyDeFioDLLex;

fragment ALPHABETIC: [a-zA-Z];

fragment ALPHANUM: [a-zA-Z0-9];

SYSTEMGRAPH_LIT: 'systemgraph';

VERTEX_LIT: 'vertex';

EDGE_LIT: 'edge';

FROM_LIT: 'from';

TO_LIT: 'to';

PORT_LIT: 'port';

NUMERIC: [0-9];

QUALIFIER: '::';

CLARIFIER: ':';

SEPARATOR: ',';

PORTS_START: '(';

PORTS_END: ')';

LIST_START: '[';

LIST_END: ']';

DICT_START: '{';

DICT_END: '}';

INTEGER: ('-')?NUMERIC+('_i')?;
BOOLEAN: NUMERIC'_b';
LONG: ('-')?NUMERIC+'_l';
REAL: ('-')?(NUMERIC*'.'NUMERIC+ | NUMERIC+'.'NUMERIC*)'_'NUMERIC+;

QUALIFIED_STRING
    :   ('::'|ALPHABETIC)('::'|'_'|ALPHANUM)*
;

INLINE_SYMBOLS: ('*'|'.'|'='|';'|'_'|'+'|'<'|'>'|'-');

WS : [ \t\r\n]+ -> skip ;

QUOTE_START: '"' -> pushMode(QUOTE_MODE);

mode QUOTE_MODE;

ESCAPED_QUOTE: '\\"';

QUOTED_STRING: (~'"'|ESCAPED_QUOTE)+;

QUOTE_END: '"' -> popMode;

//ANY: ~[\r\n]+;

//TRIPLE_QUOTE_END: '"""' (','|WS) -> popMode;