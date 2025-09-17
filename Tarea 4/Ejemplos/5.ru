# Ejemplo 5: Sistema Simple de Calificaciones
# Este programa integra todas las caracteristicas del lenguaje Ru simplificado

imprime "=== Sistema de Calificaciones UAM ===";

# Variables globales del sistema
var totalEstudiantes = 0;
var sumaCalificaciones = 0;
var estudiantesAprobados = 0;

# Datos de estudiantes (simulando entrada)
imprime "--- Procesando estudiantes ---";

# Estudiante 1
var nombre1 = "Ana Lopez";
var mat1 = 8;
var fis1 = 7;
var prog1 = 9;

imprime "=== Estudiante: " + nombre1 + " ===";
imprime "Matematicas: " + mat1;
imprime "Fisica: " + fis1;
imprime "Programacion: " + prog1;

var promedio1 = (mat1 + fis1 + prog1) / 3;
var estado1 = "REPROBADO";
if (promedio1 >= 6) {
    estado1 = "APROBADO";
}

imprime "Promedio: " + promedio1;
imprime "Estado: " + estado1;

# Actualizar estadisticas
totalEstudiantes = totalEstudiantes + 1;
sumaCalificaciones = sumaCalificaciones + promedio1;
if (promedio1 >= 6) {
    estudiantesAprobados = estudiantesAprobados + 1;
}

log "Estudiante " + nombre1 + " registrado con promedio " + promedio1;

# Estudiante 2
var nombre2 = "Carlos Ruiz";
var mat2 = 5;
var fis2 = 6;
var prog2 = 4;

imprime "=== Estudiante: " + nombre2 + " ===";
imprime "Matematicas: " + mat2;
imprime "Fisica: " + fis2;
imprime "Programacion: " + prog2;

var promedio2 = (mat2 + fis2 + prog2) / 3;
var estado2 = "REPROBADO";
if (promedio2 >= 6) {
    estado2 = "APROBADO";
}

imprime "Promedio: " + promedio2;
imprime "Estado: " + estado2;

# Actualizar estadisticas
totalEstudiantes = totalEstudiantes + 1;
sumaCalificaciones = sumaCalificaciones + promedio2;
if (promedio2 >= 6) {
    estudiantesAprobados = estudiantesAprobados + 1;
}

log "Estudiante " + nombre2 + " registrado con promedio " + promedio2;

# Estudiante 3
var nombre3 = "Maria Garcia";
var mat3 = 9;
var fis3 = 8;
var prog3 = 10;

imprime "=== Estudiante: " + nombre3 + " ===";
imprime "Matematicas: " + mat3;
imprime "Fisica: " + fis3;
imprime "Programacion: " + prog3;

var promedio3 = (mat3 + fis3 + prog3) / 3;
var estado3 = "REPROBADO";
if (promedio3 >= 6) {
    estado3 = "APROBADO";
}

imprime "Promedio: " + promedio3;
imprime "Estado: " + estado3;

# Actualizar estadisticas
totalEstudiantes = totalEstudiantes + 1;
sumaCalificaciones = sumaCalificaciones + promedio3;
if (promedio3 >= 6) {
    estudiantesAprobados = estudiantesAprobados + 1;
}

log "Estudiante " + nombre3 + " registrado con promedio " + promedio3;

# Mostrar estadisticas generales
imprime "=== Estadisticas Generales ===";
imprime "Total de estudiantes registrados: " + totalEstudiantes;

if (totalEstudiantes > 0) {
    var promedioGeneral = sumaCalificaciones / totalEstudiantes;
    var porcentajeAprobados = (estudiantesAprobados * 100) / totalEstudiantes;

    imprime "Promedio general del grupo: " + promedioGeneral;
    imprime "Estudiantes aprobados: " + estudiantesAprobados;
    imprime "Porcentaje de aprobacion: " + porcentajeAprobados + "%";

    if (promedioGeneral >= 8) {
        imprime "Excelente desempeno del grupo!";
    } else {
        if (promedioGeneral >= 7) {
            imprime "Buen desempeno del grupo";
        } else {
            if (promedioGeneral >= 6) {
                imprime "Desempeno regular del grupo";
            } else {
                imprime "El grupo necesita mejorar";
            }
        }
    }
} else {
    imprime "No hay estudiantes registrados";
}

# Analisis adicional
if (totalEstudiantes > 1) {
    imprime "=== Analisis Adicional ===";

    var tasaAprobacion = (estudiantesAprobados * 100) / totalEstudiantes;

    if (tasaAprobacion == 100) {
        imprime "Felicitaciones! Todos los estudiantes aprobaron";
    } else {
        if (tasaAprobacion >= 80) {
            imprime "Muy buena tasa de aprobacion";
        } else {
            if (tasaAprobacion >= 60) {
                imprime "Tasa de aprobacion aceptable";
            } else {
                imprime "Se requiere atencion especial para mejorar resultados";
            }
        }
    }

    # Buscar mejor y peor estudiante
    var mejorPromedio = promedio1;
    var mejorNombre = nombre1;
    var peorPromedio = promedio1;
    var peorNombre = nombre1;

    if (promedio2 > mejorPromedio) {
        mejorPromedio = promedio2;
        mejorNombre = nombre2;
    }

    if (promedio3 > mejorPromedio) {
        mejorPromedio = promedio3;
        mejorNombre = nombre3;
    }

    if (promedio2 < peorPromedio) {
        peorPromedio = promedio2;
        peorNombre = nombre2;
    }

    if (promedio3 < peorPromedio) {
        peorPromedio = promedio3;
        peorNombre = nombre3;
    }

    imprime "Mejor estudiante: " + mejorNombre + " con promedio " + mejorPromedio;
    imprime "Estudiante que necesita apoyo: " + peorNombre + " con promedio " + peorPromedio;
}

# Calcular estadisticas por materia
var promedioMat = (mat1 + mat2 + mat3) / 3;
var promedioFis = (fis1 + fis2 + fis3) / 3;
var promedioProg = (prog1 + prog2 + prog3) / 3;

imprime "=== Promedios por Materia ===";
imprime "Matematicas: " + promedioMat;
imprime "Fisica: " + promedioFis;
imprime "Programacion: " + promedioProg;

# Determinar materia mas dificil
var materiaDificil = "Matematicas";
var promedioBajo = promedioMat;

if (promedioFis < promedioBajo) {
    materiaDificil = "Fisica";
    promedioBajo = promedioFis;
}

if (promedioProg < promedioBajo) {
    materiaDificil = "Programacion";
    promedioBajo = promedioProg;
}

imprime "Materia mas dificil: " + materiaDificil + " (promedio: " + promedioBajo + ")";

imprime "=== Fin del Sistema de Calificaciones ===";
log "Sistema ejecutado para " + totalEstudiantes + " estudiantes";