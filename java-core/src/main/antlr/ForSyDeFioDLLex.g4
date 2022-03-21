lexer grammar ForSyDeFioDLLex;

QUALIFIED_ID
    :   ('::'|[A-Za-z])('::'|[A-Za-z0-9])*
;

ALPHANUM
    :  [A-Za-z0-9]+
;

WS : [ \t\r\n]+ -> skip ;

INTEGER: ('-')?[0-9]+('_i')?;
LONG: ('-')?[0-9]+'_l';
REAL: ('-')?([0-9]*'.'[0-9]+ | [0-9]+'.'[0-9]*)'_'[1-9][0-9]*;

TRIPLE_QUOTE_START: '"""' WS -> pushMode(INLINE_MODE);

mode INLINE_MODE;

//ANY: ~[\r\n]+;

TRIPLE_QUOTE_END: '"""' (','|WS) -> popMode;