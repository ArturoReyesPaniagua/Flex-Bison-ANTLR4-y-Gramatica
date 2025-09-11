%{
#include <stdio.h>

FILE *yyout;  // Declaración global
%}

%%

.    { fputc(yytext[0], yyout); }  /* Copia cualquier caracter */
\n   { fputc('\n', yyout); }       /* Manejo de saltos de línea */

%%

int main(int argc, char *argv[]) {
    if (argc > 1) {
        yyin = fopen(argv[1], "r");
        if (!yyin) {
            perror("No se pudo abrir el archivo de entrada");
            return 1;
        }
    }
    if (argc > 2) {
        yyout = fopen(argv[2], "w");
        if (!yyout) {
            perror("No se pudo abrir el archivo de salida");
            return 1;
        }
    }

    yylex();  // ejecuta el scanner

    if (yyin) fclose(yyin);
    if (yyout) fclose(yyout);

    return 0;
}
