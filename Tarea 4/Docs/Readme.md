# Traductor del Lenguaje Ru

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![ANTLR](https://img.shields.io/badge/ANTLR-4.x-blue.svg)](https://www.antlr.org/)
[![License](https://img.shields.io/badge/License-Academic-green.svg)](LICENSE)

## 📋 Descripción

Traductor completo para el lenguaje de programación **Ru** desarrollado como parte de la práctica de **Traductores de Lenguajes de Programación** en la **Universidad Autónoma Metropolitana Cuajimalpa**.

El proyecto implementa un intérprete usando **ANTLR4** con el patrón **Visitor** para procesar y ejecutar código escrito en el lenguaje Ru, proporcionando una interfaz gráfica amigable para la edición y ejecución de programas.

## ✨ Características Principales

### 🎯 Lenguaje Ru
- **Declaración de variables** con `var`
- **Tipos de datos**: enteros, decimales, strings, booleanos, nil
- **Operadores aritméticos**: `+`, `-`, `*`, `/`, `%`, `^`
- **Operadores relacionales**: `<`, `>`, `<=`, `>=`, `==`, `!=`
- **Operadores lógicos**: `&&`, `||`, `!`
- **Estructuras de control**: `if/else`, `while`
- **Entrada/salida**: `imprime`, `log`
- **Comentarios** con `#`

### 🖥️ Interfaz Gráfica
- **Editor de código** con sintaxis clara
- **Ejecución en tiempo real**
- **Visualización de resultados** separada (salida + logs)
- **Carga y guardado de archivos** `.ru`
- **Manejo de errores** visual
- **Atajos de teclado** (Ctrl+Enter para ejecutar)

### ⚙️ Arquitectura Técnica
- **Analizador léxico y sintáctico** generado con ANTLR4
- **Intérprete** implementado con patrón Visitor (`MyVisitor`)
- **Manejo robusto de errores** léxicos y sintácticos
- **Interfaz gráfica** desarrollada en Java Swing

## 🚀 Instalación y Uso

### Prerrequisitos
- **Java JDK 8** o superior
- **ANTLR 4.x** (JAR completo)
- Sistema operativo: Windows, Linux, o macOS

### Pasos de Instalación

1. **Clonar o descargar el proyecto**
```bash
# Si tienes git
git clone [URL_DEL_REPOSITORIO]
cd traductor-ru

# O descomprimir el archivo ZIP
```

2. **Generar archivos de ANTLR**
```bash
# Con comando antlr4 (si está configurado)
antlr4 Ru.g4

# O con JAR directo
java -jar antlr-4.x.x-complete.jar Ru.g4
```

3. **Compilar el proyecto**
```bash
# Linux/macOS
javac -cp ".:antlr-4.x.x-complete.jar" *.java

# Windows
javac -cp ".;antlr-4.x.x-complete.jar" *.java
```

4. **Ejecutar la aplicación**
```bash
# Linux/macOS
java -cp ".:antlr-4.x.x-complete.jar" GUIRu

# Windows
java -cp ".;antlr-4.x.x-complete.jar" GUIRu
```

## 📁 Estructura del Proyecto

```
traductor-ru/
├── Ru.g4                    # Gramática del lenguaje Ru
├── GUIRu.java              # Interfaz gráfica de usuario
├── MyVisitor.java          # Intérprete usando patrón Visitor
├── ejemplos/               # Programas de ejemplo
│   ├── ejemplo1.ru         # Variables y operaciones básicas
│   ├── ejemplo2.ru         # Estructuras de control
│   ├── ejemplo3.ru         # Bucles avanzados
│   ├── ejemplo4.ru         # Manejo de strings
│   ├── ejemplo5.ru         # Sistema completo
│   └── test_basico.ru      # Prueba simple
├── docs/
│   ├── LEEME.txt          # Instrucciones detalladas
│   └── preguntas.md       # Respuestas de la práctica
├── README.md              # Este archivo
└── generados/             # Archivos generados por ANTLR
    ├── RuLexer.java       # Analizador léxico
    ├── RuParser.java      # Analizador sintáctico
    ├── RuBaseVisitor.java # Clase base del visitor
    └── *.tokens           # Archivos de tokens
```

## 💻 Uso de la Aplicación

### Interfaz Principal
1. **Panel izquierdo**: Editor de código Ru
2. **Panel derecho**: Salida de ejecución (resultados + logs)
3. **Barra superior**: Controles (cargar, ejecutar, limpiar, guardar)
4. **Barra inferior**: Estado de la aplicación

### Comandos Principales
- **Ejecutar código**: Botón "Ejecutar" o `Ctrl+Enter`
- **Cargar archivo**: Botón "Cargar Archivo" (formatos .ru, .txt)
- **Guardar salida**: Botón "Guardar Salida" (guarda resultados en .txt)
- **Limpiar todo**: Botón "Limpiar" (reinicia editor y salida)

### Ejemplo de Código
```ruby
# Programa simple en Ru
var nombre = "Estudiante";
var edad = 20;

imprime "Hola " + nombre;
imprime "Tienes " + edad + " años";

if (edad >= 18) {
    imprime "Eres mayor de edad";
    log "Usuario adulto verificado";
} else {
    imprime "Eres menor de edad";
}

var contador = 1;
while (contador <= 3) {
    imprime "Contando: " + contador;
    contador = contador + 1;
}
```

## 📚 Ejemplos Incluidos

| Archivo | Descripción | Características |
|---------|-------------|-----------------|
| `ejemplo1.ru` | Variables y operaciones básicas | `var`, operadores aritméticos, `imprime`, `log` |
| `ejemplo2.ru` | Estructuras de control | `if/else`, `while`, operadores lógicos |
| `ejemplo3.ru` | Algoritmos matemáticos | Factorial, Fibonacci, números primos |
| `ejemplo4.ru` | Manejo de strings | Concatenación, validaciones, formateo |
| `ejemplo5.ru` | Sistema de calificaciones | Programa complejo integrando todas las características |

## 🛠️ Componentes Técnicos

### Analizador Léxico (RuLexer)
- Reconoce **tokens** del lenguaje Ru
- Maneja **palabras clave**, **operadores**, **literales**
- Ignora **comentarios** y **espacios en blanco**
- Detecta **caracteres inválidos**

### Analizador Sintáctico (RuParser)
- Construye el **Árbol de Sintaxis Abstracta (AST)**
- Implementa la **gramática completa** del lenguaje
- Maneja **precedencia de operadores**
- Reporta **errores sintácticos** con ubicación

### Intérprete (MyVisitor)
- Implementa el **patrón Visitor** de ANTLR
- **Ejecuta el código** recorriendo el AST
- Maneja **tabla de símbolos** para variables
- Genera **salida formateada** y **logs de depuración**

### Interfaz Gráfica (GUIRu)
- **Editor de código** con características básicas
- **Visualización de resultados** en tiempo real
- **Manejo de archivos** para carga y guardado
- **Reportes de errores** amigables al usuario

## 🔧 Resolución de Problemas

### Error: "Cannot find symbol"
```bash
# Regenerar archivos de ANTLR
rm -f RuLexer.java RuParser.java RuBaseVisitor.java *.tokens
antlr4 Ru.g4
javac -cp ".:antlr-4.x.x-complete.jar" *.java
```

### Error: "ClassNotFoundException"
```bash
# Verificar classpath correcto
java -cp ".:antlr-4.x.x-complete.jar" GUIRu
```

### Error de sintaxis en código Ru
- Verificar que todas las sentencias terminen con `;`
- Asegurar que los bloques `if/while` usen llaves `{}`
- Revisar que las strings estén entre comillas `""`

## 📖 Documentación Adicional

- **[LEEME.txt](LEEME.txt)**: Instrucciones detalladas de compilación y uso
- **[preguntas.md](preguntas.md)**: Respuestas a las preguntas de la práctica
- **[Gramática Ru.g4](Ru.g4)**: Especificación completa del lenguaje

## 🎓 Información Académica

**Universidad**: Universidad Autónoma Metropolitana Cuajimalpa  
**Materia**: Traductores de Lenguajes de Programación  
**Profesor**: Dr. Roberto Bernal Jaquez  
**Práctica**: Tarea 4 - Traductor en ANTLR para el Lenguaje Ru

## 📝 Licencia

Este proyecto es desarrollado con fines académicos para la UAM Cuajimalpa.

## 🤝 Contribuciones

Este es un proyecto académico. Para sugerencias o reportes de errores, contacta a los desarrolladores del equipo.

---

**Desarrollado con ❤️ para UAM Cuajimalpa**