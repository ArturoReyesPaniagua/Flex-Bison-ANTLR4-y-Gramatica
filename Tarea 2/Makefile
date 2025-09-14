# Makefile para Calculadora Científica con Flex y Bison
# Autor: [Tu nombre y matrícula]
# UEA: Traductores 25-P

# Variables del compilador
CC = gcc
CFLAGS = -Wall -Wextra -std=c99 -g
LIBS = -lm

# Nombres de archivos
LEX_FILE = calc.l
YACC_FILE = calc.y
TARGET = calculadora

# Archivos generados automáticamente
LEX_OUTPUT = lex.yy.c
YACC_OUTPUT = calc.tab.c
YACC_HEADER = calc.tab.h

# Archivos objeto
OBJECTS = $(YACC_OUTPUT:.c=.o) $(LEX_OUTPUT:.c=.o)

# Regla principal
all: $(TARGET)

# Compilación del ejecutable final
$(TARGET): $(OBJECTS)
	$(CC) $(CFLAGS) -o $@ $^ $(LIBS)
	@echo "✓ Calculadora compilada exitosamente como '$(TARGET)'"

# Generación de archivos desde Bison
$(YACC_OUTPUT) $(YACC_HEADER): $(YACC_FILE)
	bison -d $(YACC_FILE)
	@echo "✓ Archivos de Bison generados"

# Generación de archivos desde Flex
$(LEX_OUTPUT): $(LEX_FILE) $(YACC_HEADER)
	flex $(LEX_FILE)
	@echo "✓ Archivos de Flex generados"

# Compilación de archivos objeto
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Regla para limpiar archivos generados
clean:
	rm -f $(OBJECTS) $(LEX_OUTPUT) $(YACC_OUTPUT) $(YACC_HEADER) $(TARGET)
	@echo "✓ Archivos de compilación eliminados"

# Regla para limpiar completamente (incluyendo archivos de respaldo)
distclean: clean
	rm -f *~ *.bak core

# Regla para ejecutar la calculadora
run: $(TARGET)
	./$(TARGET)

# Regla para mostrar ayuda
help:
	@echo "Makefile para Calculadora Científica"
	@echo ""
	@echo "Targets disponibles:"
	@echo "  all        - Compila la calculadora (default)"
	@echo "  clean      - Elimina archivos generados"
	@echo "  distclean  - Limpieza completa"
	@echo "  run        - Compila y ejecuta la calculadora"
	@echo "  check-deps - Verifica dependencias del sistema"
	@echo "  debug      - Muestra información de diagnóstico"
	@echo "  help       - Muestra esta ayuda"
	@echo ""
	@echo "Dependencias requeridas:"
	@echo "  - flex"
	@echo "  - bison"
	@echo "  - gcc"
	@echo "  - libm (biblioteca matemática)"

# Regla para verificar dependencias
check-deps:
	@echo "Verificando dependencias..."
	@which flex > /dev/null || (echo "ERROR: flex no encontrado" && exit 1)
	@which bison > /dev/null || (echo "ERROR: bison no encontrado" && exit 1)
	@which gcc > /dev/null || (echo "ERROR: gcc no encontrado" && exit 1)
	@echo "✓ Todas las dependencias están disponibles"

# Declarar targets que no son archivos
.PHONY: all clean distclean run help check-deps

# Información adicional sobre dependencias
$(LEX_OUTPUT): $(YACC_HEADER)
$(YACC_OUTPUT:.c=.o): $(YACC_HEADER)