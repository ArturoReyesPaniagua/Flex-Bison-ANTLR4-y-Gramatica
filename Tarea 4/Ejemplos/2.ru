# Ejemplo 2: Estructuras de Control (if/else, while)
# Este programa demuestra el uso de condicionales y bucles

imprime "=== Ejemplo 2: Estructuras de Control ===";

# Estructura if/else
var edad = 20;
var categoria = "indefinido";

if (edad < 13) {
    categoria = "Nino";
} else {
    if (edad < 18) {
        categoria = "Adolescente";
    } else {
        if (edad < 65) {
            categoria = "Adulto";
        } else {
            categoria = "Adulto Mayor";
        }
    }
}

imprime "Edad: " + edad + " anos";
imprime "Categoria: " + categoria;

# Determinar si un numero es par o impar
var numero = 7;
var residuo = numero % 2;
if (residuo == 0) {
    imprime numero + " es par";
} else {
    imprime numero + " es impar";
}

# Bucle while - cuenta regresiva
imprime "Iniciando cuenta regresiva desde 5:";
var contador = 5;
while (contador > 0) {
    imprime "Cuenta: " + contador;
    contador = contador - 1;
}
imprime "Despegue!";

# Bucle while - suma de numeros
var suma = 0;
var i = 1;
while (i <= 10) {
    suma = suma + i;
    log "Sumando " + i + ", total parcial: " + suma;
    i = i + 1;
}
imprime "La suma de numeros del 1 al 10 es: " + suma;

# Verificacion de condiciones multiples
var x = 15;
var y = 25;

var condicion1 = x > 10;
var condicion2 = y > 20;
var ambas = condicion1 && condicion2;

if (ambas) {
    imprime "Ambas condiciones son verdaderas";
}

var alguna = x < 5 || y > 20;
if (alguna) {
    imprime "Al menos una condicion es verdadera";
}

log "Estructuras de control ejecutadas correctamente";