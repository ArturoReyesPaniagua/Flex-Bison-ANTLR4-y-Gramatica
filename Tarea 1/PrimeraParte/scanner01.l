%{
/*
*scanner01.l
*Ejercicios de la tarea 1 para la novela de:
*Alumno: Arturo Reyes 
*/

#include <stdio.h>
#include <string.h>
#include <strings.h>  /* para strcasecmp y strcasestr en Linux */

/* variables globales */
int total_palabras = 0;
int total_puntuacion = 0;
int max_longitud = 0;
char palabra_mas_larga[256];
int frecuencia_palabra_mas_larga = 0;
int cuenta_amor = 0;
int cuenta_amor_derivados = 0;
int cuenta_odio = 0;

/* Lista de adverbios de tiempo */
const char* adverbios[] = {
    "antes","después","luego","pronto","tarde","temprano","todavía",
    "aún","ya","ayer","hoy","mañana","anteayer","siempre","nunca",
    "jamás","próximamente","prontamente","anoche","enseguida",
    "ahora","anteriormente"
};
int frecuencia_adverbios[22] = {0};
%}

%%

[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+ {
    total_palabras++;
    int len = strlen(yytext);

    if (len > max_longitud) {
        max_longitud = len;
        strcpy(palabra_mas_larga, yytext);
        frecuencia_palabra_mas_larga = 1;
    } else if (len == max_longitud && strcmp(palabra_mas_larga, yytext) == 0) {
        frecuencia_palabra_mas_larga++;
    }

    if (strcasecmp(yytext, "amor") == 0) cuenta_amor++;
    if (strcasestr(yytext, "amor")) cuenta_amor_derivados++;
    if (strcasecmp(yytext, "odio") == 0) cuenta_odio++;

    for (int i=0; i<22; i++) {
        if (strcasecmp(yytext, adverbios[i]) == 0) {
            frecuencia_adverbios[i]++;
            break;
        }
    }
}

[.,;:!?¿¡]   { total_puntuacion++; }
[ \t\n]+     ;
.            ;

%%

int main(int argc, char **argv) {
    printf("Hola soy el analizador léxico del alumno Arturo Reyes.\n"
           "Realizaré las siguientes funciones:\n"
           "i) ¿Cómo se llama la novela que escogió?\n"
           "ii) ¿Cuántas palabras tiene la novela?\n"
           "iii) ¿Cuál es la palabra más larga o con mayor número de caracteres?\n"
           "iv) ¿Cuántos signos de puntuación tiene la novela?\n"
           "v) ¿Cuántas veces aparece la palabra 'amor'?\n"
           "vi) ¿Cuántas veces aparecen palabras con la cadena 'amor'?\n"
           "vii) ¿Cuántas veces aparece la palabra 'odio'?\n"
           "viii) ¿Cuál es el adverbio de tiempo más frecuente?\n\n");

    if (argc > 1){
        FILE *f = fopen(quijote.txt, "r");
        if (!f){
            perror("Error abriendo archivo");
            return 1;
        }
        yyin = f;
    }
    yylex();

    if (argc > 1) {
        printf("i) Novela analizada: %s\n", argv[1]);
    }
    printf("ii) Total de palabras: %d\n", total_palabras);
    printf("iii) Palabra más larga: %s (longitud %d, aparece %d veces)\n", 
            palabra_mas_larga, max_longitud, frecuencia_palabra_mas_larga);
    printf("iv) Total signos puntuación: %d\n", total_puntuacion);
    printf("v) Palabra 'amor': %d veces\n", cuenta_amor);
    printf("vi) Palabras con 'amor': %d veces\n", cuenta_amor_derivados);
    printf("vii) Palabra 'odio': %d veces\n", cuenta_odio);

    int max = 0, idx = -1;
    for (int i=0; i<22; i++) {
        if (frecuencia_adverbios[i] > max) {
            max = frecuencia_adverbios[i];
            idx = i;
        }
    }
    if (idx != -1) {
        printf("viii) Adverbio más frecuente: %s (%d veces)\n", adverbios[idx], max);
    } else {
        printf("viii) No se encontraron adverbios de tiempo.\n");
    }
    return 0;
}
