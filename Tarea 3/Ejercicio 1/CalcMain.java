import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.Scanner;
import java.util.Map;

public class CalcMain {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        MyCalcVisitor visitor = new MyCalcVisitor();

        System.out.println("=== Calculadora Científica con Variables ===");
        System.out.println("Operaciones: +, -, *, /, ^, sin(), cos(), log()");
        System.out.println("Variables: x = 5, y = x + 2");
        System.out.println("Comandos especiales:");
        System.out.println("  'salir' - terminar programa");
        System.out.println("  'vars'  - mostrar variables");
        System.out.println("  'help'  - mostrar ejemplos");
        System.out.println();

        while (true) {
            System.out.print("calc> ");
            String input = scanner.nextLine().trim();

            // Comandos especiales
            if (input.equals("salir")) {
                break;
            }

            if (input.equals("vars")) {
                Map<String, Double> vars = visitor.getVariables();
                if (vars.isEmpty()) {
                    System.out.println("No hay variables definidas");
                } else {
                    System.out.println("Variables definidas:");
                    for (Map.Entry<String, Double> entry : vars.entrySet()) {
                        System.out.println("  " + entry.getKey() + " = " + entry.getValue());
                    }
                }
                continue;
            }

            if (input.equals("help")) {
                System.out.println("Ejemplos de uso:");
                System.out.println("  x = 5          (asignar variable)");
                System.out.println("  y = x * 2      (usar variable en expresión)");
                System.out.println("  sin(x)         (función trigonométrica)");
                System.out.println("  log(100)       (logaritmo base 10)");
                System.out.println("  2^3            (potencia)");
                System.out.println("  (x + y) * 2    (expresión con paréntesis)");
                continue;
            }

            if (input.isEmpty()) {
                continue;
            }

            try {
                // Crear el input stream
                ANTLRInputStream inputStream = new ANTLRInputStream(input);

                // Crear lexer
                CalcLexer lexer = new CalcLexer(inputStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // Crear parser
                CalcParser parser = new CalcParser(tokens);

                // Configurar manejo de errores
                parser.removeErrorListeners();
                parser.addErrorListener(new BaseErrorListener() {
                    @Override
                    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                            int line, int charPositionInLine,
                                            String msg, RecognitionException e) {
                        throw new RuntimeException("Error de sintaxis en posición " + charPositionInLine + ": " + msg);
                    }
                });

                // Parsear desde la regla 'prog'
                ParseTree tree = parser.prog();

                // Evaluar con visitor
                Double result = visitor.visit(tree);

                // Solo mostrar resultado si no es una asignación
                if (!input.contains("=")) {
                    System.out.println("Resultado: " + result);
                }

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("\nVariables finales: " + visitor.getVariables());
        System.out.println("¡Hasta luego!");
    }
}