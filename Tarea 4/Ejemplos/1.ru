# Ejemplo 1: Variables y Operaciones Basicas
# Este programa demuestra declaracion de variables, asignaciones y operaciones aritmeticas

var a = 10;
var b = 5;
var nombre = "Usuario";

imprime "=== Ejemplo 1: Variables y Operaciones ===";
imprime "Nombre: " + nombre;
imprime "a = " + a;
imprime "b = " + b;

# Operaciones aritmeticas
var suma = a + b;
var resta = a - b;
var multiplicacion = a * b;
var division = a / b;
var potencia = a ^ 2;

imprime "Suma: " + a + " + " + b + " = " + suma;
imprime "Resta: " + a + " - " + b + " = " + resta;
imprime "Multiplicacion: " + a + " * " + b + " = " + multiplicacion;
imprime "Division: " + a + " / " + b + " = " + division;
imprime "Potencia: " + a + "^2 = " + potencia;

# Operaciones logicas
var mayor = a > b;
var igual = a == b;

imprime "a > b? " + mayor;
imprime "a == b? " + igual;

log "Programa ejemplo1.ru ejecutado correctamente";