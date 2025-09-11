%{
/*
* scanner03.l
* Ejercicio de desencriptación
* Alumno: Arturo Reyes
*/

#include <stdio.h>
#define N 7  /* Corrimiento usado en el encriptador */
%}

%%

[a-z] {
    /* Corrimiento inverso para minusculas */
    char c = ((yytext[0] - 'a' - N + 26) % 26) + 'a';
    fputc(c, yyout);
}

[A-Z] {
    /* Corrimiento inverso para mayúsculas */
    char c = ((yytext[0] - 'A' - N + 26) % 26) + 'A';
    fputc(c, yyout);
}


(.|\n) { fputs(yytext, yyout); }

%%

int main(int argc, char **argv) {
    printf("Desencriptador con corrimiento de 7 posiciones\n");

    if (argc < 3) {
        fprintf(stderr, "Uso: %s <archivo_encriptado> <archivo_salida>\n", argv[0]);
        return 1;
    }

    FILE *in = fopen(argv[1], "r");
    if (!in) {
        perror("No se pudo abrir archivo de entrada");
        return 1;
    }

    FILE *out = fopen(argv[2], "w");
    if (!out) {
        perror("No se pudo crear archivo de salida");
        fclose(in);
        return 1;
    }

    yyin = in;
    yyout = out;

    yylex();

    fclose(in);
    fclose(out);

    printf("Archivo desencriptado guardado en %s\n", argv[2]);
    return 0;
}
