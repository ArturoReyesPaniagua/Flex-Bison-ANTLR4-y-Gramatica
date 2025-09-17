# Tarea 3: Introducción al Patrón Visitor en ANTLR4

**Universidad Autónoma Metropolitana - Cuajimalpa**  
**Materia:** Traductores  
**Profesor:** Dr. Roberto Bernal Jaquez  
**Estudiante:**  Arturo Reyes Paniagua
**Fecha:** 24 de julio de 2025

## Descripción General

Esta tarea implementa dos ejercicios principales usando ANTLR4 y el patrón Visitor:

1. **Calculadora Científica con Variables**: Evaluador de expresiones aritméticas, funciones trigonométricas y manejo de variables
2. **Traductor MiniLang**: Traductor de un mini-lenguaje de programación a código Java ejecutable

## Estructura del Proyecto

```
tarea3-antlr-visitor/
├── ejercicio1/                 # Calculadora Científica
│   ├── Calc.g4                # Gramática principal con variables
│   ├── MyCalcVisitor.java     # Visitor evaluador
│   ├── CalcMain.java          # Programa principal interactivo
│   └── build_calc.sh          # Script de compilación
├── ejercicio2/                 # Traductor MiniLang
│   ├── MiniLang.g4            # Gramática del mini-lenguaje
│   ├── MiniLangToJavaVisitor.java # Visitor traductor
│   ├── MiniLangMain.java      # Programa principal
│   ├── build_minilang.sh      # Script de compilación
│   ├── ejemplo.ml             # Programa de ejemplo (factorial)
│   ├── simple.ml              # Ejemplo básico
│   └── fibonacci.ml           # Ejemplo Fibonacci
├── ejemplos/                   # Casos de prueba adicionales
├── RESPUESTAS.md              # Respuestas a preguntas didácticas
├── README.md                  # Este archivo
└── demo_completo.sh           # Demo de ambos ejercicios
```

## Requisitos

- **Java 8+**
- **ANTLR 4.13.11 o 4.13.12**
- **Sistema operativo**: Linux/macOS (recomendado) o Windows

## Instalación y Compilación

### Opción 1: Demo Completo (Recomendado)

```bash
# Ejecutar demo de ambos ejercicios
chmod +x demo_completo.sh
./demo_completo.sh
```

### Opción 2: Compilación Individual

#### Ejercicio 1: Calculadora Científica

```bash
cd ejercicio1
chmod +x build_calc.sh
./build_calc.sh
```

#### Ejercicio 2: Traductor MiniLang

```bash
cd ejercicio2
chmod +x build_minilang.sh
./build_minilang.sh
```

### Opción 3: Compilación Manual

```bash
# Para cualquier ejercicio:
antlr4 -visitor Gramatica.g4
javac *.java
```

## Ejercicio 1: Calculadora Científica con Variables

### Características Implementadas

- ✅ **Operadores aritméticos**: `+`, `-`, `*`, `/`, `^` (potencia)
- ✅ **Funciones científicas**: `sin()`, `cos()`, `log()` (base 10)
- ✅ **Variables persistentes**: `x = 5`, `y = x * 2`
- ✅ **Precedencia correcta**: Potencia > Mult/Div > Suma/Resta
- ✅ **Paréntesis**: `(x + y) * 2`
- ✅ **Manejo de errores**: División por cero, log de negativos
- ✅ **Modo interactivo**: Variables se mantienen entre comandos

### Uso

```bash
cd ejercicio1
java CalcMain
```

### Ejemplos de Uso

```
calc> x = 10
x = 10.0
calc> y = x * 2
y = 20.0
calc> sin(x)
Resultado: -0.5440211108893698
calc> log(100)
Resultado: 2.0
calc> z = (x + y) / 2
z = 15.0
calc> vars
Variables definidas:
  x = 10.0
  y = 20.0
  z = 15.0
calc> salir
```

### Comandos Especiales

- `vars` - Mostrar variables definidas
- `help` - Mostrar ejemplos de uso
- `salir` - Terminar programa

## Ejercicio 2: Traductor MiniLang a Java

### Características del Lenguaje MiniLang

**Tipos de datos:**
- Enteros únicamente

**Variables:**
- Declaración: `let nombre = valor`
- Asignación: `nombre = valor`

**Operadores:**
- Aritméticos: `+`, `-`, `*`, `/`
- Comparación: `>`, `>=`, `<`, `<=`, `==`, `!=`

**Estructuras de control:**
- Condicional: `if condicion then ... endif`
- Bucle: `while condicion do ... endwhile`

**Entrada/Salida:**
- Impresión: `print expresion`

### Uso

```bash
cd ejercicio2
java MiniLangMain archivo.ml
```

### Ejemplo de Traducción

**Archivo `ejemplo.ml`:**
```
let x = 10
let y = 5
print x + y
if x > y then
    print x
    let z = x * 2
    print z
endif
let i = 0
while i < 3 do
    print i
    i = i + 1
endwhile
```

**Se traduce a `ejemplo.java`:**
```java
public class ejemplo {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        System.out.println((x + y));
        if ((x > y)) {
            System.out.println(x);
            int z = (x * 2);
            System.out.println(z);
        }
        int i = 0;
        while ((i < 3)) {
            System.out.println(i);
            i = (i + 1);
        }
    }
}
```

**Compilar y ejecutar:**
```bash
javac ejemplo.java
java ejemplo
```

**Salida:**
```
15
10
20
0
1
2
```

## Preguntas Didácticas y Respuestas

### Ejercicio 1: Calculadora Científica con Visitor

#### 1. ¿Por qué es útil el patrón Visitor en este contexto?

El patrón Visitor es útil porque:

- **Separación de responsabilidades**: Separa la lógica de evaluación de la estructura del árbol sintáctico
- **Extensibilidad**: Podemos agregar nuevas operaciones (evaluación, impresión, optimización) sin modificar la gramática
- **Reutilización**: El mismo árbol sintáctico puede ser procesado por diferentes visitors
- **Mantenibilidad**: Cada tipo de operación queda encapsulada en su propio método
- **Type Safety**: ANTLR genera interfaces tipadas que previenen errores en tiempo de compilación

#### 2. ¿Cómo maneja tu implementación la precedencia de operadores?

La precedencia se maneja mediante **el orden de las alternativas en la gramática**:

```antlr
expr
    : expr '^' expr         # Precedencia más alta (potencia)
    | 'sin' '(' expr ')'    # Funciones
    | 'cos' '(' expr ')'    
    | 'log' '(' expr ')'    
    | expr '*' expr         # Multiplicación/División
    | expr '/' expr         
    | expr '+' expr         # Suma/Resta (precedencia más baja)
    | expr '-' expr         
    | '(' expr ')'          # Paréntesis anulan precedencia
    | NUM                   # Literales
    | ID                    # Variables
```

**Nota**: La gramática tiene asociatividad izquierda para todos los operadores, pero la potencia debería ser asociativa a la derecha matemáticamente.

#### 3. Si quisieras añadir soporte para variables, ¿qué cambios harías? Muestra la gramática modificada y el MyVisitorV2 (versión 2) modificado.

**Cambios realizados en Calc.g4:**

La gramática ya incluye soporte completo para variables:

```antlr
grammar Calc;

prog: stat EOF;

stat: ID '=' expr    # assign
    | expr           # printExpr
    ;

expr
    : expr '^' expr         # PowerExpr
    | 'sin' '(' expr ')'    # SinExpr
    | 'cos' '(' expr ')'    # CosExpr
    | 'log' '(' expr ')'    # LogExpr
    | expr '*' expr         # MulExpr
    | expr '/' expr         # DivExpr
    | expr '+' expr         # AddExpr
    | expr '-' expr         # SubExpr
    | '(' expr ')'          # ParenExpr
    | NUM                   # NumberExpr
    | ID                    # VarExpr
    ;

NUM : [0-9]+('.'[0-9]+)?;
ID  : [a-zA-Z_][a-zA-Z0-9_]*;
WS  : [ \t\r\n]+ -> skip ;
```

**Cambios en MyCalcVisitor.java:**

1. **Tabla de símbolos**: `Map<String, Double> variables` para almacenar variables
2. **Método visitAssign**: Para manejar asignaciones `x = 5`
3. **Método visitVarExpr**: Para resolver referencias a variables
4. **Validación**: Error si se referencia una variable no definida

#### 4. ¿Qué sucede si se ingresa una expresión inválida como sin()?

Se produce un **error de parsing** porque:
- `sin()` requiere un argumento según la gramática: `'sin' '(' expr ')'`
- El parser espera una expresión válida entre los paréntesis
- ANTLR genera un `RecognitionException` que capturamos y mostramos al usuario con un mensaje claro

### Ejercicio 2: Traductor de un Lenguaje Simple

#### 1. ¿Cómo aseguras la precedencia?

La precedencia se asegura mediante **el orden de las alternativas**:

```antlr
expr : expr op=('>'|'>='|'<'|'<='|'=='|'!=') expr  # Comparison (baja)
     | expr op=('*'|'/') expr   # MulDiv (mayor precedencia)
     | expr op=('+'|'-') expr   # AddSub (menor precedencia)
     | '(' expr ')'             # Paréntesis
     | NUMERO | ID              # Operandos
```

Los operadores de multiplicación y división aparecen **antes** que suma y resta, estableciendo su mayor precedencia.

#### 2. ¿Qué limitaciones tiene este traductor actualmente?

**Limitaciones principales**:

- **Tipos limitados**: Solo maneja enteros
- **Sin funciones**: No hay definición ni llamada de funciones
- **Sin arrays**: No hay estructuras de datos complejas
- **Sin else**: No hay cláusula else en if
- **Sin break/continue**: No hay control de flujo avanzado
- **Sin manejo de errores**: No hay try/catch
- **Sin scope**: Todas las variables son globales
- **Sin comentarios**: No hay soporte para comentarios en el código# Tarea 3: Introducción al Patrón Visitor en ANTLR4

**Universidad Autónoma Metropolitana - Cuajimalpa**  
**Materia:** Traductores  
**Profesor:** Dr. Roberto Bernal Jaquez  
**Estudiante:** [Tu nombre aquí]  
**Compañero:** [Nombre del compañero si aplica]  
**Fecha:** 24 de julio de 2025

## Descripción General

Esta tarea implementa dos ejercicios principales usando ANTLR4 y el patrón Visitor:

1. **Calculadora Científica con Variables**: Evaluador de expresiones aritméticas, funciones trigonométricas y manejo de variables
2. **Traductor MiniLang**: Traductor de un mini-lenguaje de programación a código Java ejecutable

## Estructura del Proyecto

```
tarea3-antlr-visitor/
├── ejercicio1/                 # Calculadora Científica
│   ├── Calc.g4                # Gramática principal con variables
│   ├── MyCalcVisitor.java     # Visitor evaluador
│   ├── CalcMain.java          # Programa principal interactivo
│   └── build_calc.sh          # Script de compilación
├── ejercicio2/                 # Traductor MiniLang
│   ├── MiniLang.g4            # Gramática del mini-lenguaje
│   ├── MiniLangToJavaVisitor.java # Visitor traductor
│   ├── MiniLangMain.java      # Programa principal
│   ├── build_minilang.sh      # Script de compilación
│   ├── ejemplo.ml             # Programa de ejemplo (factorial)
│   ├── simple.ml              # Ejemplo básico
│   └── fibonacci.ml           # Ejemplo Fibonacci
├── ejemplos/                   # Casos de prueba adicionales
├── RESPUESTAS.md              # Respuestas a preguntas didácticas
├── README.md                  # Este archivo
```

## Requisitos

- **Java 8+**
- **ANTLR 4.13.11 o 4.13.12**
- **Sistema operativo**: Linux/macOS (recomendado) o Windows

## Instalación y Compilación

### Opción 1: Demo Completo (Recomendado)

```bash
# Ejecutar demo de ambos ejercicios
chmod +x demo_completo.sh
./demo_completo.sh
```

### Opción 2: Compilación Individual

#### Ejercicio 1: Calculadora Científica

```bash
cd ejercicio1
chmod +x build_calc.sh
./build_calc.sh
```

#### Ejercicio 2: Traductor MiniLang

```bash
cd ejercicio2
chmod +x build_minilang.sh
./build_minilang.sh
```

### Opción 3: Compilación Manual

```bash
# Para cualquier ejercicio:
antlr4 -visitor Gramatica.g4
javac *.java
```

## Ejercicio 1: Calculadora Científica con Variables

### Características Implementadas

- ✅ **Operadores aritméticos**: `+`, `-`, `*`, `/`, `^` (potencia)
- ✅ **Funciones científicas**: `sin()`, `cos()`, `log()` (base 10)
- ✅ **Variables persistentes**: `x = 5`, `y = x * 2`
- ✅ **Precedencia correcta**: Potencia > Mult/Div > Suma/Resta
- ✅ **Paréntesis**: `(x + y) * 2`
- ✅ **Manejo de errores**: División por cero, log de negativos
- ✅ **Modo interactivo**: Variables se mantienen entre comandos

### Uso

```bash
cd ejercicio1
java CalcMain
```

### Ejemplos de Uso

```
calc> x = 10
x = 10.0
calc> y = x * 2
y = 20.0
calc> sin(x)
Resultado: -0.5440211108893698
calc> log(100)
Resultado: 2.0
calc> z = (x + y) / 2
z = 15.0
calc> vars
Variables definidas:
  x = 10.0
  y = 20.0
  z = 15.0
calc> salir
```

### Comandos Especiales

- `vars` - Mostrar variables definidas
- `help` - Mostrar ejemplos de uso
- `salir` - Terminar programa

## Ejercicio 2: Traductor MiniLang a Java

### Características del Lenguaje MiniLang

**Tipos de datos:**
- Enteros únicamente

**Variables:**
- Declaración: `let nombre = valor`
- Asignación: `nombre = valor`

**Operadores:**
- Aritméticos: `+`, `-`, `*`, `/`
- Comparación: `>`, `>=`, `<`, `<=`, `==`, `!=`

**Estructuras de control:**
- Condicional: `if condicion then ... endif`
- Bucle: `while condicion do ... endwhile`

**Entrada/Salida:**
- Impresión: `print expresion`

### Uso

```bash
cd ejercicio2
java MiniLangMain archivo.ml
```

### Ejemplo de Traducción

**Archivo `ejemplo.ml`:**
```
let x = 10
let y = 5
print x + y
if x > y then
    print x
    let z = x * 2
    print z
endif
let i = 0
while i < 3 do
    print i
    i = i + 1
endwhile
```

**Se traduce a `ejemplo.java`:**
```java
public class ejemplo {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        System.out.println((x + y));
        if ((x > y)) {
            System.out.println(x);
            int z = (x * 2);
            System.out.println(z);
        }
        int i = 0;
        while ((i < 3)) {
            System.out.println(i);
            i = (i + 1);
        }
    }
}
```

**Compilar y ejecutar:**
```bash
javac ejemplo.java
java ejemplo
```

**Salida:**
```
15
10
20
0
1
2
```

## Preguntas Didácticas y Respuestas

### Ejercicio 1: Calculadora Científica con Visitor

#### 1. ¿Por qué es útil el patrón Visitor en este contexto?

El patrón Visitor es útil porque:

- **Separación de responsabilidades**: Separa la lógica de evaluación de la estructura del árbol sintáctico
- **Extensibilidad**: Podemos agregar nuevas operaciones (evaluación, impresión, optimización) sin modificar la gramática
- **Reutilización**: El mismo árbol sintáctico puede ser procesado por diferentes visitors
- **Mantenibilidad**: Cada tipo de operación queda encapsulada en su propio método
- **Type Safety**: ANTLR genera interfaces tipadas que previenen errores en tiempo de compilación

#### 2. ¿Cómo maneja tu implementación la precedencia de operadores?

La precedencia se maneja mediante **el orden de las alternativas en la gramática**:

```antlr
expr
    : expr '^' expr         # Precedencia más alta (potencia)
    | 'sin' '(' expr ')'    # Funciones
    | 'cos' '(' expr ')'    
    | 'log' '(' expr ')'    
    | expr '*' expr         # Multiplicación/División
    | expr '/' expr         
    | expr '+' expr         # Suma/Resta (precedencia más baja)
    | expr '-' expr         
    | '(' expr ')'          # Paréntesis anulan precedencia
    | NUM                   # Literales
    | ID                    # Variables
```

**Nota**: La gramática tiene asociatividad izquierda para todos los operadores, pero la potencia debería ser asociativa a la derecha matemáticamente.

#### 3. Si quisieras añadir soporte para variables, ¿qué cambios harías? Muestra la gramática modificada y el MyVisitorV2 (versión 2) modificado.

**Cambios realizados en Calc.g4:**

La gramática ya incluye soporte completo para variables:

```antlr
grammar Calc;

prog: stat EOF;

stat: ID '=' expr    # assign
    | expr           # printExpr
    ;

expr
    : expr '^' expr         # PowerExpr
    | 'sin' '(' expr ')'    # SinExpr
    | 'cos' '(' expr ')'    # CosExpr
    | 'log' '(' expr ')'    # LogExpr
    | expr '*' expr         # MulExpr
    | expr '/' expr         # DivExpr
    | expr '+' expr         # AddExpr
    | expr '-' expr         # SubExpr
    | '(' expr ')'          # ParenExpr
    | NUM                   # NumberExpr
    | ID                    # VarExpr
    ;

NUM : [0-9]+('.'[0-9]+)?;
ID  : [a-zA-Z_][a-zA-Z0-9_]*;
WS  : [ \t\r\n]+ -> skip ;
```

**Cambios en MyCalcVisitor.java:**

1. **Tabla de símbolos**: `Map<String, Double> variables` para almacenar variables
2. **Método visitAssign**: Para manejar asignaciones `x = 5`
3. **Método visitVarExpr**: Para resolver referencias a variables
4. **Validación**: Error si se referencia una variable no definida

#### 4. ¿Qué sucede si se ingresa una expresión inválida como sin()?

Se produce un **error de parsing** porque:
- `sin()` requiere un argumento según la gramática: `'sin' '(' expr ')'`
- El parser espera una expresión válida entre los paréntesis
- ANTLR genera un `RecognitionException` que capturamos y mostramos al usuario con un mensaje claro

### Ejercicio 2: Traductor de un Lenguaje Simple

#### 1. ¿Cómo aseguras la precedencia?

La precedencia se asegura mediante **el orden de las alternativas**:

```antlr
expr : expr op=('>'|'>='|'<'|'<='|'=='|'!=') expr  # Comparison (baja)
     | expr op=('*'|'/') expr   # MulDiv (mayor precedencia)
     | expr op=('+'|'-') expr   # AddSub (menor precedencia)
     | '(' expr ')'             # Paréntesis
     | NUMERO | ID              # Operandos
```

Los operadores de multiplicación y división aparecen **antes** que suma y resta, estableciendo su mayor precedencia.

#### 2. ¿Qué limitaciones tiene este traductor actualmente?

**Limitaciones principales**:

- **Tipos limitados**: Solo maneja enteros
- **Sin funciones**: No hay definición ni llamada de funciones
- **Sin arrays**: No hay estructuras de datos complejas
- **Sin else**: No hay cláusula else en if
- **Sin break/continue**: No hay control de flujo avanzado
- **Sin manejo de errores**: No hay try/catch
- **Sin scope**: Todas las variables son globales
- **Sin comentarios**: No hay soporte para comentarios en el código

#### 3. ¿Podrías usar JavaScript en vez de Java qué cambiaría?

**Cambios principales**:

1. **Generación del parser**: `antlr4 -Dlanguage=JavaScript MiniLang.g4`
2. **Sintaxis del visitor**: JavaScript tiene sintaxis más flexible
3. **Tipos dinámicos**: No necesitaríamos declarar tipos explícitos
4. **Código generado**:
```javascript
// En lugar de: int x = 5;
let x = 5;

// En lugar de: System.out.println(x);
console.log(x);
```

**Ventajas**: Sintaxis más simple, menos verbosa  
**Desventajas**: Sin verificación de tipos en tiempo de compilación

#### 4. ¿Por qué es importante separar el parser del visitor?

**Razones clave**:

- **Reutilización**: El mismo parser puede usarse para múltiples propósitos (evaluación, traducción, análisis)
- **Mantenibilidad**: Cambios en la lógica de procesamiento no afectan el parsing
- **Extensibilidad**: Podemos agregar nuevos visitors sin modificar la gramática
- **Responsabilidad única**: Parser se encarga solo de estructura sintáctica, visitor de semántica
- **Testing**: Podemos probar parsing y procesamiento por separado
- **Múltiples targets**: Un parser puede generar código para diferentes lenguajes usando diferentes visitors

## Casos de Prueba

### Ejercicio 1: Calculadora

```bash
# Pruebas automáticas incluidas en build_calc.sh
echo "x = 5" | java CalcMain
echo "y = x * 2" | java CalcMain
echo "sin(1.5708)" | java CalcMain
echo "log(100) + 2^3" | java CalcMain
```

### Ejercicio 2: Traductor

```bash
# Ejemplos incluidos
java MiniLangMain ejemplo.ml      # Ejemplo completo con factorial
java MiniLangMain simple.ml       # Ejemplo básico
java MiniLangMain fibonacci.ml    # Secuencia de Fibonacci
```

## Limitaciones Conocidas

### Ejercicio 1: Calculadora
- Precedencia de potencia es asociativa izquierda (debería ser derecha)
- Solo números de punto flotante
- Funciones trigonométricas en radianes

### Ejercicio 2: Traductor
- Solo tipos enteros
- Sin funciones definidas por usuario
- Sin arrays u otras estructuras de datos
- Condiciones booleanas basadas en comparaciones explícitas

## Extensiones Futuras

### Ejercicio 1:
- Soporte para más funciones matemáticas (tan, sqrt, exp)
- Variables con diferentes tipos de datos
- Constantes matemáticas (pi, e)
- Funciones definidas por usuario

### Ejercicio 2:
- Soporte para más tipos de datos (float, string, boolean)
- Funciones definidas por usuario
- Arrays y estructuras de datos
- Estructuras de control avanzadas (for, do-while, switch)
- Manejo de errores y excepciones
- Comentarios en el código
- Optimizaciones del código generado
- Soporte para otros lenguajes de destino (Python, C++)

## Archivos de Entrega

- ✅ **Archivos fuente**: `.g4`, `.java` para ambos ejercicios
- ✅ **Scripts de compilación**: `build_calc.sh`, `build_minilang.sh`
- ✅ **Ejemplos de prueba**: `ejemplo.ml`, `simple.ml`, `fibonacci.ml`
- ✅ **Documentación completa**: `README.md` con instrucciones y respuestas
- ✅ **Demo automático**: `demo_completo.sh` para probar todo

## Referencias

- [ANTLR 4 Documentation](https://github.com/antlr/antlr4)
- [The Definitive ANTLR 4 Reference](https://pragprog.com/titles/tpantlr2/the-definitive-antlr-4-reference/)
- [Visitor Pattern](https://en.wikipedia.org/wiki/Visitor_pattern)

## Autor

[Tu nombre aquí]  
Universidad Autónoma Metropolitana - Cuajimalpa

---

## Resumen Ejecutivo

Esta implementación demuestra el uso exitoso de ANTLR4 y el patrón Visitor para crear:

1. **Una calculadora científica completa** con variables persistentes, funciones matemáticas y manejo de errores robusto
2. **Un traductor de lenguaje completo** que convierte código MiniLang a Java ejecutable

Ambos ejercicios muestran las ventajas del patrón Visitor para separar la lógica de procesamiento de la estructura sintáctica, permitiendo extensibilidad y mantenibilidad del código. La implementación incluye manejo de errores, casos edge, y documentación completa para facilitar el uso y comprensión de los sistemas desarrollados.

#### 3. ¿Podrías usar JavaScript en vez de Java qué cambiaría?

**Cambios principales**:

1. **Generación del parser**: `antlr4 -Dlanguage=JavaScript MiniLang.g4`
2. **Sintaxis del visitor**: JavaScript tiene sintaxis más flexible
3. **Tipos dinámicos**: No necesitaríamos declarar tipos explícitos
4. **Código generado**:
```javascript
// En lugar de: int x = 5;
let x = 5;

// En lugar de: System.out.println(x);
console.log(x);
```

**Ventajas**: Sintaxis más simple, menos verbosa  
**Desventajas**: Sin verificación de tipos en tiempo de compilación

#### 4. ¿Por qué es importante separar el parser del visitor?

**Razones clave**:

- **Reutilización**: El mismo parser puede usarse para múltiples propósitos (evaluación, traducción, análisis)
- **Mantenibilidad**: Cambios en la lógica de procesamiento no afectan el parsing
- **Extensibilidad**: Podemos agregar nuevos visitors sin modificar la gramática
- **Responsabilidad única**: Parser se encarga solo de estructura sintáctica, visitor de semántica
- **Testing**: Podemos probar parsing y procesamiento por separado
- **Múltiples targets**: Un parser puede generar código para diferentes lenguajes usando diferentes visitors

## Casos de Prueba

### Ejercicio 1: Calculadora

```bash
# Pruebas automáticas incluidas en build_calc.sh
echo "x = 5" | java CalcMain
echo "y = x * 2" | java CalcMain
echo "sin(1.5708)" | java CalcMain
echo "log(100) + 2^3" | java CalcMain
```

### Ejercicio 2: Traductor

```bash
# Ejemplos incluidos
java MiniLangMain ejemplo.ml      # Ejemplo completo con factorial
java MiniLangMain simple.ml       # Ejemplo básico
java MiniLangMain fibonacci.ml    # Secuencia de Fibonacci
```

## Limitaciones Conocidas

### Ejercicio 1: Calculadora
- Precedencia de potencia es asociativa izquierda (debería ser derecha)
- Solo números de punto flotante
- Funciones trigonométricas en radianes

### Ejercicio 2: Traductor
- Solo tipos enteros
- Sin funciones definidas por usuario
- Sin arrays u otras estructuras de datos
- Condiciones booleanas basadas en comparaciones explícitas

## Extensiones Futuras

### Ejercicio 1:
- Soporte para más funciones matemáticas (tan, sqrt, exp)
- Variables con diferentes tipos de datos
- Constantes matemáticas (pi, e)
- Funciones definidas por usuario

### Ejercicio 2:
- Soporte para más tipos de datos (float, string, boolean)
- Funciones definidas por usuario
- Arrays y estructuras de datos
- Estructuras de control avanzadas (for, do-while, switch)
- Manejo de errores y excepciones
- Comentarios en el código
- Optimizaciones del código generado
- Soporte para otros lenguajes de destino (Python, C++)

## Archivos de Entrega

- ✅ **Archivos fuente**: `.g4`, `.java` para ambos ejercicios


## Referencias

- [ANTLR 4 Documentation](https://github.com/antlr/antlr4)
- [The Definitive ANTLR 4 Reference](https://pragprog.com/titles/tpantlr2/the-definitive-antlr-4-reference/)
- [Visitor Pattern](https://en.wikipedia.org/wiki/Visitor_pattern)

## Autor

Arturo Ryees Paniagua
Universidad Autónoma Metropolitana - Cuajimalpa

---

## Resumen EjecutivoS

Esta implementación demuestra el uso exitoso de ANTLR4 y el patrón Visitor para crear:

1. **Una calculadora científica completa** con variables persistentes, funciones matemáticas y manejo de errores robusto
2. **Un traductor de lenguaje completo** que convierte código MiniLang a Java ejecutable

Ambos ejercicios muestran las ventajas del patrón Visitor para separar la lógica de procesamiento de la estructura sintáctica, permitiendo extensibilidad y mantenibilidad del código. La implementación incluye manejo de errores, casos edge, y documentación completa para facilitar el uso y comprensión de los sistemas desarrollados.
