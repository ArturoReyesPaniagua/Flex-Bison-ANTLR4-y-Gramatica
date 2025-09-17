grammar Calc;

// Programa: una sola expresión o asignación
prog: stat EOF;

// Statement: puede ser asignación o expresión
stat: ID '=' expr    # assign
    | expr           # printExpr
    ;

expr
    : expr '^' expr         # PowerExpr
    | 'sin' '(' expr ')'    # SinExpr
    | 'cos' '(' expr ')'    # CosExpr
    | 'log' '(' expr ')'    # LogExpr
    | expr '*' expr         # MulExpr
    | expr '/' expr         # DivExpr
    | expr '+' expr         # AddExpr
    | expr '-' expr         # SubExpr
    | '(' expr ')'          # ParenExpr
    | NUM                   # NumberExpr
    | ID                    # VarExpr
    ;

NUM : [0-9]+('.'[0-9]+)?;
ID  : [a-zA-Z_][a-zA-Z0-9_]*;
WS  : [ \t\r\n]+ -> skip ;