grammar Ru;

programa
 : sentencia* EOF
 ;

sentencia
 : declaracion
 | asignacion
 | imprimir
 | log
 | sentencia_if
 | sentencia_while
 | OTRO {System.err.println("caracter desconocido: " + $OTRO.text);}
 ;

declaracion
 : VAR ID ASIGNA expr PTOCOMA
 ;

asignacion
 : ID ASIGNA expr PTOCOMA
 ;

imprimir
 : IMPRIMIR expr PTOCOMA
 ;

log
 : LOG expr PTOCOMA
 ;

sentencia_if
 : IF APAR expr CPAR bloque_if (ELSE bloque_else)?
 ;

bloque_if
 : ALLAVE sentencia* CLLAVE
 ;

bloque_else
 : ALLAVE sentencia* CLLAVE
 ;

sentencia_while
 : WHILE APAR expr CPAR bloque_while
 ;

bloque_while
 : ALLAVE sentencia* CLLAVE
 ;

expr
 : expr POW<assoc=right> expr           #powExpr
 | MENOS expr                           #menosUnarioExpr
 | NOT expr                             #notExpr
 | expr op=(MULT | DIV | MOD) expr      #multExpr
 | expr op=(MAS | MENOS) expr           #addExpr
 | expr op=(MAYORQ | MENORQ | MAYIG | MENIG) expr #compExpr
 | expr op=(IGUAL | DIFQ) expr          #equalExpr
 | expr AND expr                        #andExpr
 | expr OR expr                         #orExpr
 | APAR expr CPAR                       #parExpr
 | ID                                   #idExpr
 | INT                                  #intExpr
 | FLOAT                                #floatExpr
 | STRING                               #stringExpr
 | TRUE                                 #trueExpr
 | FALSE                                #falseExpr
 | NIL                                  #nilExpr
 ;

// Palabras clave
VAR : 'var';
IF : 'if';
ELSE : 'else';
WHILE : 'while';
TRUE : 'true';
FALSE : 'false';
NIL : 'nil';
LOG : 'log';
IMPRIMIR : 'imprime';

// Operadores
OR : '||';
AND : '&&';
IGUAL : '==';
DIFQ : '!=';
MAYORQ : '>';
MENORQ : '<';
MENIG : '<=';
MAYIG : '>=';
MAS : '+';
MENOS : '-';
MULT : '*';
DIV : '/';
MOD : '%';
POW : '^';
NOT : '!';

// Delimitadores
PTOCOMA : ';';
ASIGNA : '=';
APAR : '(';
CPAR : ')';
ALLAVE : '{';
CLLAVE : '}';

// Literales
ID : [a-zA-Z_] [a-zA-Z_0-9]* ;
INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]* | '.' [0-9]+ ;
STRING : '"' (~["\r\n] | '""')* '"' ;

// Ignorar
COMENTARIO : '#' ~[\r\n]* -> skip ;
ESPACIO : [ \t\r\n] -> skip ;
OTRO : . ;