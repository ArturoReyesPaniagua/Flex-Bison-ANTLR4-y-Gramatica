grammar MiniLang;

programa : instruccion+ EOF ;

instruccion :
    'let' ID '=' expr (NL | EOF)        # Declaracion
    | ID '=' expr (NL | EOF)            # Asignacion
    | 'print' expr (NL | EOF)           # Imprimir
    | 'if' expr 'then' NL
      instruccion+
      'endif' (NL | EOF)                # If
    | 'while' expr 'do' NL
      instruccion+
      'endwhile' (NL | EOF)             # While
    | NL                                # BlankLine
    ;

expr : expr op=('>'|'>='|'<'|'<='|'=='|'!=') expr  # Comparison
     | expr op=('*'|'/') expr   # MulDiv
     | expr op=('+'|'-') expr   # AddSub
     | '(' expr ')'             # Parentesis
     | NUMERO                   # Literal
     | ID                       # Variable
     ;

NUMERO : [0-9]+ ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
NL : '\r'? '\n' ;
WS : [ \t]+ -> skip ;