%{
#include <stdio.h>
#define N 7
FILE *yyout;   // <- Declaración global
%}

%%

[a-z] { fputc(((yytext[0]-'a'+N)%26)+'a', yyout); }
[A-Z] { fputc(((yytext[0]-'A'+N)%26)+'A', yyout); }
(.|\n) { fputs(yytext, yyout); }

%%

int main(int argc, char **argv) {
    if(argc<3){
        fprintf(stderr,"Uso: %s <archivo_entrada> <archivo_salida>\n",argv[0]);
        return 1;
    }

    FILE *in=fopen(argv[1],"r");
    if(!in){ perror("No se pudo abrir archivo de entrada"); return 1; }

    FILE *out=fopen(argv[2],"w");
    if(!out){ perror("No se pudo crear archivo de salida"); fclose(in); return 1; }

    yyin = in;
    yyout = out;

    yylex();  // ejecuta el scanner

    fclose(in);
    fclose(out);

    printf("Archivo encriptado guardado en %s\n", argv[2]);
    return 0;
}
