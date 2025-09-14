%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <errno.h>

/* Declaraciones de funciones */
int yylex(void);
void yyerror(const char *s);
double validate_log(double x);
double validate_sqrt(double x);
double validate_division(double x, double y);
double validate_modulo(double x, double y);
%}

/* Definición de tipos de datos para los tokens */
%union {
    double num;
}

/* Declaración de tokens */
%token <num> NUMBER
%token PLUS MINUS MULTIPLY DIVIDE MODULO
%token LPAREN RPAREN COMMA
%token SIN COS TAN LOG LOG10 SQRT POW
%token EXIT

/* Definición de tipos para las reglas */
%type <num> expression term factor primary function

/* Precedencia y asociatividad de operadores */
%left PLUS MINUS                    /* Menor precedencia */
%left MULTIPLY DIVIDE MODULO        /* Mayor precedencia */
%right UMINUS                       /* Operador unario menos */
%left LPAREN RPAREN                 /* Paréntesis */

/* Símbolo inicial */
%start input

%%

/* Reglas de la gramática */

input:
    /* vacío */
    | input line
    ;

line:
    '\n'                { /* línea vacía */ }
    | expression '\n'   { printf("= %.10g\n", $1); }
    | EXIT '\n'         { printf("¡Adiós!\n"); exit(0); }
    | error '\n'        { yyerrok; }
    ;

expression:
    term                           { $$ = $1; }
    | expression PLUS term         { $$ = $1 + $3; }
    | expression MINUS term        { $$ = $1 - $3; }
    ;

term:
    factor                         { $$ = $1; }
    | term MULTIPLY factor         { $$ = $1 * $3; }
    | term DIVIDE factor           { $$ = validate_division($1, $3); }
    | term MODULO factor           { $$ = validate_modulo($1, $3); }
    ;

factor:
    primary                        { $$ = $1; }
    | MINUS factor %prec UMINUS    { $$ = -$2; }
    | PLUS factor %prec UMINUS     { $$ = $2; }
    ;

primary:
    NUMBER                         { $$ = $1; }
    | LPAREN expression RPAREN     { $$ = $2; }
    | function                     { $$ = $1; }
    ;

function:
    SIN LPAREN expression RPAREN   { $$ = sin($3); }
    | COS LPAREN expression RPAREN { $$ = cos($3); }
    | TAN LPAREN expression RPAREN { $$ = tan($3); }
    | LOG LPAREN expression RPAREN { $$ = validate_log($3); }
    | LOG10 LPAREN expression RPAREN { $$ = log10($3) ; }
    | SQRT LPAREN expression RPAREN { $$ = validate_sqrt($3); }
    | POW LPAREN expression COMMA expression RPAREN { $$ = pow($3, $5); }
    ;

%%

/* Implementación de funciones */

void yyerror(const char *s) {
    printf("Error sintáctico: %s\n", s);
}

double validate_log(double x) {
    if (x <= 0) {
        printf("Error semántico: logaritmo de número no positivo (%.2f)\n", x);
        return 0.0;
    }
    return log(x);
}

double validate_log10(double x) {
    if (x <= 0) {
        printf("Error semántico: logaritmo base 10 de número no positivo (%.2f)\n", x);
        return 0.0;
    }
    return log10(x);
}

double validate_sqrt(double x) {
    if (x < 0) {
        printf("Error semántico: raíz cuadrada de número negativo (%.2f)\n", x);
        return 0.0;
    }
    return sqrt(x);
}

double validate_division(double x, double y) {
    if (y == 0.0) {
        printf("Error semántico: división por cero\n");
        return 0.0;
    }
    return x / y;
}

double validate_modulo(double x, double y) {
    if (y == 0.0) {
        printf("Error semántico: módulo por cero\n");
        return 0.0;
    }
    return fmod(x, y);
}

int main(void) {
    printf("=== Calculadora Científica ===\n");
    printf("Ingrese expresiones matemáticas o 'exit'/'quit' para salir.\n");
    printf("Funciones disponibles: sin, cos, tan, log, log10, sqrt, pow\n");
    printf("Constantes disponibles: PI, E\n");
    printf("Ejemplo: sin(PI/2), sqrt(25), pow(2,3)\n");
    printf(">>> ");
    
    return yyparse();
}