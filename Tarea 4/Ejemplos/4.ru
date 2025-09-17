# Ejemplo 4: Manejo de Strings y Validaciones
# Este programa demuestra operaciones con cadenas y validaciones

imprime "=== Ejemplo 4: Manejo de Strings ===";

# Datos de ejemplo (simulando entrada del usuario)
var nombre = "Juan Perez";
var edad = 25;
var ciudad = "Mexico";

# Mostrar informacion
imprime "=== Informacion Personal ===";
imprime "Nombre: " + nombre;
imprime "Edad: " + edad + " anos";
imprime "Ciudad: " + ciudad;

# Determinar categoria por edad
var categoria = "indefinido";
if (edad < 18) {
    categoria = "menor de edad";
} else {
    categoria = "mayor de edad";
}

imprime "Hola " + nombre + ", eres " + categoria;

# Simulacion de calculadora con datos predefinidos
imprime "=== Calculadora Simple ===";
var num1 = 15;
var num2 = 7;

imprime "Operaciones con " + num1 + " y " + num2 + ":";

# Realizar operaciones
var suma = num1 + num2;
var resta = num1 - num2;
var mult = num1 * num2;

imprime "Suma: " + suma;
imprime "Resta: " + resta;
imprime "Multiplicacion: " + mult;

if (num2 != 0) {
    var div = num1 / num2;
    imprime "Division: " + div;
} else {
    imprime "No se puede dividir por cero";
}

# Procesamiento de nombres
imprime "Procesando nombre: " + nombre;
log "Usuario procesado: " + nombre;
var nombreFormal = "Sr./Sra. " + nombre;
imprime "Nombre formal: " + nombreFormal;

# Validacion de edad
var validacion = "indefinido";
if (edad < 0) {
    validacion = "Edad invalida: no puede ser negativa";
} else {
    if (edad > 150) {
        validacion = "Edad invalida: demasiado alta";
    } else {
        validacion = "Edad valida";
    }
}

imprime "Validacion de edad: " + validacion;

# Trabajo con multiples strings
var saludo = "Hola";
var despedida = "Adios";
var frase1 = saludo + " " + nombre;
var frase2 = despedida + " " + nombre;

imprime frase1;
imprime frase2;

# Concatenacion compleja
var mensaje = "Gracias " + nombre + " de " + ciudad + " por usar nuestro programa.";
imprime mensaje;

# Operaciones con numeros y strings
var precio = 100;
var descuento = 15;
var precioFinal = precio - descuento;
var mensajePrecio = "Precio original: $" + precio + ", descuento: $" + descuento + ", precio final: $" + precioFinal;
imprime mensajePrecio;

# Validaciones booleanas
var esValido = edad > 0 && edad < 150;
if (esValido) {
    imprime "Los datos son validos";
} else {
    imprime "Los datos contienen errores";
}

log "Programa de manejo de strings completado";