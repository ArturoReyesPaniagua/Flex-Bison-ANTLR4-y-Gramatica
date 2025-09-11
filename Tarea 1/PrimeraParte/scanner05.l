%{
/*
 * scanner05.l
 * Contador de palabras con Flex
 * Alumno: Arturo Reyes
 */

#include <stdio.h>

int total_palabras = 0;   // Contador de palabras global
%}

%%

[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+   { total_palabras++; }  /* Coincide con cualquier palabra */

[ \t\n]+    ;    /* Ignorar espacios, tabulaciones y saltos de línea */
.           ;    /* Ignorar cualquier otro carácter */

%%

int main(int argc, char **argv) {
    if (argc < 2) {
        fprintf(stderr, "Uso: %s <archivo_entrada>\n", argv[0]);
        return 1;
    }

    FILE *in = fopen(argv[1], "r");
    if (!in) {
        perror("No se pudo abrir archivo de entrada");
        return 1;
    }

    yyin = in;   // Asignar archivo de entrada a Flex

    yylex();    // Ejecutar el scanner

    fclose(in);

    printf("El archivo '%s' tiene %d palabras.\n", argv[1], total_palabras);
    return 0;
}
