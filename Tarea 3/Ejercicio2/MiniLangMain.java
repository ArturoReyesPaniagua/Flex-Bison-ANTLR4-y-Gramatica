import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MiniLangMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Uso: java MiniLangMain <archivo.ml>");
            System.err.println("Ejemplo: java MiniLangMain programa.ml");
            return;
        }

        String filename = args[0];

        try {
            // Leer archivo de entrada
            String input = new String(Files.readAllBytes(Paths.get(filename)));

            System.out.println("=== Traductor MiniLang a Java ===");
            System.out.println("Archivo de entrada: " + filename);
            System.out.println("\n--- Código MiniLang ---");
            System.out.println(input);

            // Crear el input stream
            ANTLRInputStream inputStream = new ANTLRInputStream(input);

            // Crear lexer
            MiniLangLexer lexer = new MiniLangLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Crear parser
            MiniLangParser parser = new MiniLangParser(tokens);

            // Configurar manejo de errores
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    throw new RuntimeException("Error de sintaxis en línea " + line +
                            ", posición " + charPositionInLine + ": " + msg);
                }
            });

            // Parsear
            ParseTree tree = parser.programa();

            // Traducir con visitor
            MiniLangToJavaVisitor visitor = new MiniLangToJavaVisitor();
            String javaCode = visitor.visit(tree);

            System.out.println("\n--- Código Java generado ---");
            System.out.println(javaCode);

            // Guardar en archivo
            String outputFile = filename.replaceAll("\\.ml$", "") + ".java";
            String className = filename.replaceAll("\\.ml$", "").replaceAll(".*[/\\\\]", "");

            // Personalizar nombre de clase basado en archivo
            String customJavaCode = javaCode.replace("class MiniLangProgram", "class " + className);

            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(customJavaCode);
            }

            System.out.println("\n✅ Traducción completada!");
            System.out.println("Archivo Java generado: " + outputFile);
            System.out.println("\nPara compilar y ejecutar:");
            System.out.println("javac " + outputFile);
            System.out.println("java " + className);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}