# Ejemplo 3: Bucles While Avanzados y Operaciones Matematicas
# Este programa demuestra bucles complejos y calculos matematicos

imprime "=== Ejemplo 3: Bucles While Avanzados ===";

# Simular bucle for con while (numeros del 1 al 5)
imprime "Numeros del 1 al 5:";
var i = 1;
while (i <= 5) {
    imprime "Numero: " + i;
    i = i + 1;
}

# Numeros pares del 2 al 10
imprime "Numeros pares del 2 al 10:";
var j = 2;
while (j <= 10) {
    imprime "Par: " + j;
    j = j + 2;
}

# Calculo de factorial
var n = 5;
var factorial = 1;
var temp = n;
while (temp > 1) {
    factorial = factorial * temp;
    temp = temp - 1;
}
imprime "Factorial de " + n + " = " + factorial;

# Otro factorial
var n2 = 7;
var factorial2 = 1;
var temp2 = n2;
while (temp2 > 1) {
    factorial2 = factorial2 * temp2;
    temp2 = temp2 - 1;
}
imprime "Factorial de " + n2 + " = " + factorial2;

# Verificar numeros primos del 2 al 10
imprime "Verificando numeros primos del 2 al 10:";
var num = 2;
while (num <= 10) {
    var esPrimo = true;
    var divisor = 2;

    if (num > 2) {
        while (divisor * divisor <= num) {
            var residuo = num % divisor;
            if (residuo == 0) {
                esPrimo = false;
            }
            divisor = divisor + 1;
        }
    }

    if (esPrimo) {
        imprime num + " es primo";
    }

    num = num + 1;
}

# Serie de Fibonacci
imprime "Primeros 8 numeros de Fibonacci:";
var a = 0;
var b = 1;
var contador = 0;

while (contador < 8) {
    if (contador == 0) {
        imprime "F(" + contador + ") = " + a;
    } else {
        if (contador == 1) {
            imprime "F(" + contador + ") = " + b;
        } else {
            var siguiente = a + b;
            imprime "F(" + contador + ") = " + siguiente;
            a = b;
            b = siguiente;
        }
    }
    contador = contador + 1;
}

# Operacion de maximo entre dos numeros
var x = 10;
var y = 25;
var maximo = x;
if (y > x) {
    maximo = y;
}
imprime "Maximo entre " + x + " y " + y + ": " + maximo;

# Otro maximo
var x2 = 100;
var y2 = 50;
var maximo2 = x2;
if (y2 > x2) {
    maximo2 = y2;
}
imprime "Maximo entre " + x2 + " y " + y2 + ": " + maximo2;

log "Bucles avanzados y operaciones matematicas completadas";