%{
/* 
 * Alumno:Arturo Reyes Paniagua
 * Tarea 1 parte 2
 * Analizador léxico Mini-C 
 * Reconoce: palabras clave, identificadores, enteros, flotantes,
 * operadores aritméticos/relacionales/lógicos, asignación, delimitadores
 * y comentarios (// y ... ). Ignora espacios en blanco.
 * Reporta errores léxicos para caracteres no válidos.
 */

#include <stdio.h>
#include <string.h>

/* Para evitar advertencias si no se usa -lfl con yywrap() */
int yywrap(void) { return 1; }

/* yyin se declara extern en Flex */
extern FILE *yyin;
%}

/* ----- Estados ----- */
%x COMMENT  

/* ----- Macros útiles ----- */
DIG     [0-9]
IDHEAD  [A-Za-z_]
IDTAIL  [A-Za-z0-9_]
WS      [ \t\r\n]+

%%

"//"[^\n]*                { /* comentario de una línea: ignorar */ }
"/*"                      { BEGIN(COMMENT); }
<COMMENT>"*/"             { BEGIN(INITIAL); }
<COMMENT>\n               { /* mantener líneas: ignorar */ }
<COMMENT>.                { /* consumir cualquier char dentro del comentario */ }
<COMMENT><<EOF>>          { fprintf(stderr, "ERROR LEXICO: Comentario no cerrado\n"); return 0; }

{WS}                      { /* ignorar */ }


"if"                      { printf("PALABRA CLAVE: %s\n", yytext); }
"else"                    { printf("PALABRA CLAVE: %s\n", yytext); }
"while"                   { printf("PALABRA CLAVE: %s\n", yytext); }
"for"                     { printf("PALABRA CLAVE: %s\n", yytext); }
"int"                     { printf("PALABRA CLAVE: %s\n", yytext); }
"float"                   { printf("PALABRA CLAVE: %s\n", yytext); }
"return"                  { printf("PALABRA CLAVE: %s\n", yytext); }
"void"                    { printf("PALABRA CLAVE: %s\n", yytext); }


{IDHEAD}{IDTAIL}*         { printf("IDENTIFICADOR: %s\n", yytext); }


{DIG}+"."{DIG}+           { printf("FLOTANTE: %s\n", yytext); }
{DIG}+                    { printf("ENTERO: %s\n", yytext); }


"&&"                      { printf("OPERADOR LOGICO: %s\n", yytext); }
"||"                      { printf("OPERADOR LOGICO: %s\n", yytext); }
"<="                      { printf("OPERADOR RELACIONAL: %s\n", yytext); }
">="                      { printf("OPERADOR RELACIONAL: %s\n", yytext); }
"=="                      { printf("OPERADOR RELACIONAL: %s\n", yytext); }
"!="                      { printf("OPERADOR RELACIONAL: %s\n", yytext); }
"+"                       { printf("OPERADOR ARITMETICO: %s\n", yytext); }
"-"                       { printf("OPERADOR ARITMETICO: %s\n", yytext); }
"*"                       { printf("OPERADOR ARITMETICO: %s\n", yytext); }
"/"                       { printf("OPERADOR ARITMETICO: %s\n", yytext); }
"%"                       { printf("OPERADOR ARITMETICO: %s\n", yytext); }
"<"                       { printf("OPERADOR RELACIONAL: %s\n", yytext); }
">"                       { printf("OPERADOR RELACIONAL: %s\n", yytext); }
"!"                       { printf("OPERADOR LOGICO: %s\n", yytext); }
"="                       { printf("OPERADOR ASIGNACION: %s\n", yytext); }


"("                       { printf("PARENTESIS IZQUIERDO: %s\n", yytext); }
")"                       { printf("PARENTESIS DERECHO: %s\n", yytext); }
"{"                       { printf("LLAVE IZQUIERDA: %s\n", yytext); }
"}"                       { printf("LLAVE DERECHA: %s\n", yytext); }
"["                       { printf("CORCHETE IZQUIERDO: %s\n", yytext); }
"]"                       { printf("CORCHETE DERECHO: %s\n", yytext); }
";"                       { printf("PUNTO Y COMA: %s\n", yytext); }
","                       { printf("COMA: %s\n", yytext); }


.                         { printf("ERROR LEXICO: Cadena no reconocida '%s'\n", yytext); }

%%

int main(int argc, char **argv) {
    if (argc < 2) {
        fprintf(stderr, "Uso: %s archivo.mc\n", argv[0]);
        return 1;
    }

    FILE *archivo = fopen(argv[1], "r");
    if (!archivo) {
        perror("Error al abrir el archivo");
        return 1;
    }

    yyin = archivo;  // Flex usará este archivo como entrada
    yylex();         // Ejecuta el analizador léxico

    fclose(archivo);
    return 0;
}
