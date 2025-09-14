Calculadora Científica con Flex y Bison

Alumno: Arturo Reyes Paniagua
Matrícula: 2193035784
UEA: Traductores 25-P
Profesor: Dr. Roberto Bernal Jaquez
Universidad: UAM Cuajimalpa
Descripción General

Este proyecto implementa una calculadora científica completa utilizando las herramientas Flex (analizador léxico) y Bison (analizador sintáctico). La calculadora es capaz de procesar expresiones matemáticas complejas, manejar funciones trigonométricas y logarítmicas, constantes matemáticas, y proporcionar un manejo robusto de errores sintácticos y semánticos.
Características Principales

    Operaciones aritméticas básicas: suma, resta, multiplicación, división y módulo
    Funciones trigonométricas: sin(), cos(), tan() (argumentos en radianes)
    Funciones logarítmicas: log() (natural) y log10() (base 10)
    Otras funciones: sqrt() (raíz cuadrada) y pow(base, exponente)
    Constantes matemáticas: PI y E
    Manejo de números negativos y operador unario
    Precedencia correcta de operadores con soporte para paréntesis
    Manejo robusto de errores sintácticos y semánticos
    Interfaz interactiva con comandos exit/quit

Instrucciones de Compilación y Ejecución
Dependencias Requeridas

    flex (generador de analizadores léxicos)
    bison (generador de analizadores sintácticos)
    gcc (compilador C)
    libm (biblioteca matemática de C)

Compilación
bash

# Verificar dependencias
make check-deps

# Compilar la calculadora
make

# O compilar y ejecutar directamente
make run

Ejecución
bash

# Ejecutar la calculadora
./calculadora

Limpieza
bash

# Limpiar archivos generados
make clean

# Limpieza completa
make distclean

Diseño Léxico (Tokens)

El analizador léxico implementado en calc.l reconoce los siguientes tipos de tokens:

Token	Patrón	Descripción	Ejemplo
NUMBER	[0-9]+ o [0-9]*\.[0-9]+	Números enteros y flotantes	42, 3.14159
PLUS	+	Operador suma	+
MINUS	-	Operador resta/negación unaria	-
MULTIPLY	*	Operador multiplicación	*
DIVIDE	/	Operador división	/
MODULO	%	Operador módulo	%
LPAREN	(	Paréntesis izquierdo	(
RPAREN	)	Paréntesis derecho	)
COMMA	,	Separador de argumentos	,
SIN	sin	Función seno	sin
COS	cos	Función coseno	cos
TAN	tan	Función tangente	tan
LOG	log	Logaritmo natural	log
LOG10	log10	Logaritmo base 10	log10
SQRT	sqrt	Raíz cuadrada	sqrt
POW	pow	Potencia	pow
PI	PI	Constante Pi (→ NUMBER)	PI
E	E	Constante Euler (→ NUMBER)	E
EXIT	exit o quit	Comando de salida	exit

Reconocimiento de la Expresión de Ejemplo

Para la expresión: (3.14159 + 2 * sin(PI/4)) / sqrt(9)

Flex la reconocería como la siguiente secuencia de tokens:

    LPAREN - (
    NUMBER - 3.14159
    PLUS - +
    NUMBER - 2
    MULTIPLY - *
    SIN - sin
    LPAREN - (
    NUMBER - PI (convertido a 3.14159...)
    DIVIDE - /
    NUMBER - 4
    RPAREN - )
    RPAREN - )
    DIVIDE - /
    SQRT - sqrt
    LPAREN - (
    NUMBER - 9
    RPAREN - )

Gramática Completa

La gramática implementada en calc.y está escrita en notación BNF y es libre de ambigüedades:
bnf

input      → ε | input line

line       → '\n'
           | expression '\n'
           | EXIT '\n'
           | error '\n'

expression → term
           | expression PLUS term
           | expression MINUS term

term       → factor
           | term MULTIPLY factor
           | term DIVIDE factor
           | term MODULO factor

factor     → primary
           | MINUS factor
           | PLUS factor

primary    → NUMBER
           | LPAREN expression RPAREN
           | function

function   → SIN LPAREN expression RPAREN
           | COS LPAREN expression RPAREN
           | TAN LPAREN expression RPAREN
           | LOG LPAREN expression RPAREN
           | LOG10 LPAREN expression RPAREN
           | SQRT LPAREN expression RPAREN
           | POW LPAREN expression COMMA expression RPAREN

Precedencia y Asociatividad

La precedencia de operadores (de menor a mayor) está definida como:

    PLUS, MINUS (asociatividad izquierda)
    MULTIPLY, DIVIDE, MODULO (asociatividad izquierda)
    UMINUS (operador unario, asociatividad derecha)
    LPAREN, RPAREN (paréntesis)

Derivación de Ejemplo: 5 + 2 * (8 - 3)

expression
├── expression PLUS term
│   ├── term
│   │   └── factor
│   │       └── primary
│   │           └── NUMBER(5)
│   ├── PLUS
│   └── term MULTIPLY factor
│       ├── term
│       │   └── factor
│       │       └── primary
│       │           └── NUMBER(2)
│       ├── MULTIPLY
│       └── factor
│           └── primary
│               └── LPAREN expression RPAREN
│                   ├── LPAREN
│                   ├── expression MINUS term
│                   │   ├── term
│                   │   │   └── factor
│                   │   │       └── primary
│                   │   │           └── NUMBER(8)
│                   │   ├── MINUS
│                   │   └── term
│                   │       └── factor
│                   │           └── primary
│                   │               └── NUMBER(3)
│                   └── RPAREN

Punto de manejo de precedencia: La precedencia se maneja en las reglas expression y term, donde term tiene mayor precedencia que expression, asegurando que la multiplicación se evalúe antes que la suma.
Decisiones de Diseño
1. Manejo de Errores

    Errores sintácticos: Manejados por Bison con la regla error y función yyerror()
    Errores semánticos: Validaciones específicas para división por cero, logaritmos de números negativos, etc.
    Recuperación: El parser se recupera automáticamente de errores sintácticos

2. Funciones Matemáticas

    Las funciones trigonométricas interpretan argumentos en radianes
    Se incluyen validaciones para evitar operaciones matemáticamente incorrectas
    La función pow() acepta dos argumentos separados por coma

3. Constantes

    PI y E se convierten directamente a tokens NUMBER en el lexer
    Esto permite su uso transparente en cualquier expresión

4. Interfaz de Usuario

    Modo interactivo con prompt visual
    Comandos exit y quit para terminar el programa
    Mensajes informativos y de ayuda al inicio

Limitaciones Conocidas

    Precisión numérica: Limitada a la precisión de double en C
    Funciones: Solo se implementan las funciones básicas solicitadas
    Variables: No se soporta asignación de variables personalizadas
    Historial: No se mantiene historial de comandos anteriores

Demostración de Resultados
Parte 1: Operaciones Aritméticas Básicas

>>> 15 / 4
= 3.75

>>> 15.0 / 4  
= 3.75

>>> 2 + 3 * 4
= 14

>>> (2 + 3) * 4
= 20

Parte 2: Números Negativos y Unarios

>>> -10 + 5
= -5

>>> 2 * -3
= -6

>>> -(5 - 2)
= -3

Parte 3: Funciones Matemáticas

>>> sin(PI / 2)
= 1

>>> sqrt(25) + pow(2, 3)
= 13

>>> log(E * E)
= 2

Parte 4: Constantes Predefinidas

>>> 2 * PI
= 6.283185307

>>> E + 1
= 3.718281828

Parte 5: Manejo de Errores

Error sintáctico:

>>> 2 + * 3
Error sintáctico: syntax error

Error semántico:

>>> 5 / 0
Error semántico: división por cero
= 0

>>> log(-5)
Error semántico: logaritmo de número no positivo (-5.00)
= 0

>>> sqrt(-4)
Error semántico: raíz cuadrada de número negativo (-4.00)
= 0

Parte 6: Sesión Interactiva
bash

$ ./calculadora
=== Calculadora Científica ===
Ingrese expresiones matemáticas o 'exit'/'quit' para salir.
Funciones disponibles: sin, cos, tan, log, log10, sqrt, pow
Constantes disponibles: PI, E
Ejemplo: sin(PI/2), sqrt(25), pow(2,3)
>>> 2 + 3 * 4
= 14
>>> sin(PI/4)
= 0.7071067812
>>> pow(2, 8)
= 256
>>> exit
¡Adiós!

Archivos del Proyecto

    calc.l - Analizador léxico (Flex)
    calc.y - Analizador sintáctico (Bison)
    Makefile - Sistema de compilación
    README.md - Documentación del proyecto

Fecha de entrega: 13 de Julio 2025
