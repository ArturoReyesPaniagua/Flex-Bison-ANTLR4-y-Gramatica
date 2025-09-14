
# Tarea 1: Análisis Léxico usando Flex

**Alumno:** Arturo Reyes Paniagua  
**Materia:** Traductores 25-P  
**Institución:** UAM Cuajimalpa  
**Profesor:** Dr. Roberto Bernal Jaquez

## Descripción General

Este proyecto está dividido en dos partes principales:

1. **Primera Parte:** Ejercicios de análisis léxico usando Flex para ganar experiencia con la herramienta
2. **Segunda Parte:** Desarrollo de un analizador léxico completo para un mini-lenguaje C

---

## Primera Parte: Ejercicios de Análisis Léxico

### Ejercicio 1: Análisis de Novela (`scanner01.l`)

**Objetivo:** Analizar un archivo de texto de una novela del proyecto Gutenberg y extraer estadísticas diversas.

**Funcionalidades:**
- Cuenta el total de palabras
- Identifica la palabra más larga y su frecuencia
- Cuenta signos de puntuación
- Busca ocurrencias de "amor" y palabras derivadas
- Busca ocurrencias de "odio"
- Identifica el adverbio de tiempo más frecuente

**Compilación y ejecución:**
```bash
flex scanner01.l
gcc lex.yy.c -o analizador01 -lfl
./analizador01 novela.txt
```

### Ejercicio 2: Encriptador César (`scanner02.l`)

**Objetivo:** Encriptar texto usando cifrado César con desplazamiento N=7.

**Funcionalidad:**
- Desplaza cada letra 7 posiciones en el alfabeto
- Mantiene mayúsculas y minúsculas
- Preserva caracteres no alfabéticos

**Compilación y ejecución:**
```bash
flex scanner02.l
gcc lex.yy.c -o encriptador -lfl
./encriptador archivo_entrada.txt archivo_salida.txt
```

### Ejercicio 3: Desencriptador César (`scanner03.l`)

**Objetivo:** Desencriptar texto cifrado con César N=7.

**Funcionalidad:**
- Revierte el desplazamiento de 7 posiciones
- Recupera el texto original

**Compilación y ejecución:**
```bash
flex scanner03.l
gcc lex.yy.c -o desencriptador -lfl
./desencriptador archivo_encriptado.txt archivo_original.txt
```

### Ejercicio 4: Copiador de Archivos (`scanner04.l`)

**Objetivo:** Copiar completamente el contenido de un archivo a otro.

**Compilación y ejecución:**
```bash
flex scanner04.l
gcc lex.yy.c -o copiador -lfl
./copiador archivo_entrada.txt archivo_salida.txt
```

### Ejercicio 5: Contador de Palabras (`scanner05.l`)

**Objetivo:** Implementar un contador de palabras similar al comando `wc`.

**Funcionalidad:**
- Cuenta palabras incluyendo caracteres especiales del español
- Ignora espacios, tabulaciones y saltos de línea

**Compilación y ejecución:**
```bash
flex scanner05.l
gcc lex.yy.c -o contador -lfl
./contador archivo.txt
```

---

## Segunda Parte: Analizador Léxico Mini-C

### Descripción del Lenguaje Mini-C

El analizador reconoce los siguientes elementos léxicos:

- **Palabras Clave:** `if`, `else`, `while`, `for`, `int`, `float`, `return`, `void`
- **Identificadores:** Secuencias de letras, dígitos y guiones bajos (no empiezan con dígito)
- **Literales Enteros:** Secuencias de dígitos
- **Literales Flotantes:** Números con punto decimal (formato: dígitos.dígitos)
- **Operadores Aritméticos:** `+`, `-`, `*`, `/`, `%`
- **Operadores Relacionales:** `<`, `>`, `<=`, `>=`, `==`, `!=`
- **Operadores Lógicos:** `&&`, `||`, `!`
- **Operador de Asignación:** `=`
- **Delimitadores:** `()`, `{}`, `[]`, `;`, `,`
- **Comentarios:** 
  - Una línea: `// comentario`
  - Múltiples líneas: `/* comentario */`

### Archivo Principal: `scanner.l`

**Características destacadas:**
- Manejo de estados para comentarios multilínea
- Detección de comentarios no cerrados
- Clasificación precisa de tokens
- Manejo de errores léxicos
- Salida formateada y clara

### Archivo de Prueba: `programa.mc`

Contiene un programa de ejemplo que incluye todos los tipos de tokens soportados por el lenguaje Mini-C.

### Compilación y Ejecución

```bash
# Generar el analizador léxico
flex scanner.l

# Compilar el código C generado
gcc lex.yy.c -o scanner -lfl

# Ejecutar con archivo de prueba
./scanner programa.mc
```

### Ejemplo de Salida

```
OPERADOR RELACIONAL: >=
ENTERO: 50
OPERADOR LOGICO: &&
IDENTIFICADOR: a
OPERADOR RELACIONAL: !=
IDENTIFICADOR: b
PARENTESIS DERECHO: )
LLAVE IZQUIERDA: {
PALABRA CLAVE: return
ENTERO: 1
PUNTO Y COMA: ;
```

---

## Estructura de Archivos

```
Tarea 1/
├── PrimeraParte/
│   ├── scanner01.l          # Analizador de novela
│   ├── scanner02.l          # Encriptador César
│   ├── scanner03.l          # Desencriptador César
│   ├── scanner04.l          # Copiador de archivos
│   └── scanner05.l          # Contador de palabras
└── SegundaParte/
    ├── scanner.l            # Analizador léxico Mini-C
    └── programa.mc          # Archivo de prueba Mini-C
```

---

## Requisitos del Sistema

- **Flex:** Fast Lexical Analyzer Generator
- **GCC:** GNU Compiler Collection
- **Sistema operativo:** Linux, macOS o Windows con herramientas de desarrollo

### Instalación de Dependencias

**Ubuntu/Debian:**
```bash
sudo apt-get install flex gcc
```

**macOS (con Homebrew):**
```bash
brew install flex gcc
```

**Windows:**
Usar MinGW o WSL con las herramientas correspondientes.

---

## Consideraciones Especiales

### Primera Parte
- Los archivos están diseñados para trabajar con archivos de texto plano
- El analizador de novela incluye caracteres especiales del español
- Los encriptadores/desencriptadores mantienen la estructura original del texto

### Segunda Parte
- El analizador maneja correctamente los comentarios anidados y no cerrados
- Se implementó un sistema de estados para comentarios multilínea
- El manejo de errores es robusto y reporta caracteres no reconocidos
- La salida está formateada para facilitar la depuración

---

## Notas del Desarrollador

Este proyecto demuestra el uso práctico de Flex para análisis léxico en diferentes contextos, desde procesamiento básico de texto hasta análisis sintáctico preliminar de lenguajes de programación. Cada ejercicio incrementa progresivamente la complejidad y muestra diferentes aspectos de las capacidades de Flex.

La implementación incluye manejo robusto de errores, comentarios claros en el código y ejemplos de prueba comprehensivos para validar la funcionalidad.
