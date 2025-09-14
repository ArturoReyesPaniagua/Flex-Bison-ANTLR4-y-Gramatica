%{
#define _USE_MATH_DEFINES
#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "calc.tab.h"

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

#ifndef M_E
#define M_E 2.7182818284590452354
#endif

void yyerror(const char *s);
%}

DIGIT       [0-9]
INTEGER     {DIGIT}+
FLOAT       {DIGIT}+\.{DIGIT}*|{DIGIT}*\.{DIGIT}+
WHITESPACE  [ \t]+

%%

{WHITESPACE}    { /* Ignorar espacios */ }
\n              { return '\n'; }

{INTEGER}       { yylval.num = atof(yytext); return NUMBER; }
{FLOAT}         { yylval.num = atof(yytext); return NUMBER; }

"+"             { return PLUS; }
"-"             { return MINUS; }
"*"             { return MULTIPLY; }
"/"             { return DIVIDE; }
"%"             { return MODULO; }
"("             { return LPAREN; }
")"             { return RPAREN; }
","             { return COMMA; }

"sin"           { return SIN; }
"cos"           { return COS; }
"tan"           { return TAN; }
"log"           { return LOG; }
"log10"         { return LOG10; }
"sqrt"          { return SQRT; }
"pow"           { return POW; }

"PI"            { yylval.num = M_PI; return NUMBER; }
"E"             { yylval.num = M_E; return NUMBER; }

"exit"          { return EXIT; }
"quit"          { return EXIT; }

.               { printf("Error: carácter no reconocido '%c'\n", yytext[0]); return yytext[0]; }

%%

int yywrap(void) {
    return 1;
}