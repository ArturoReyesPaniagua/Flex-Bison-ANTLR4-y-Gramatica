import java.util.HashSet;
import java.util.Set;

public class MiniLangToJavaVisitor extends MiniLangBaseVisitor<String> {

    private Set<String> variables = new HashSet<>();
    private int indentLevel = 0;

    /**
     * Genera la indentación apropiada según el nivel actual
     */
    private String indent() {
        return "    ".repeat(indentLevel);
    }

    @Override
    public String visitPrograma(MiniLangParser.ProgramaContext ctx) {
        StringBuilder sb = new StringBuilder();

        // Generar cabecera del programa Java
        sb.append("public class MiniLangProgram {\n");
        sb.append("    public static void main(String[] args) {\n");
        indentLevel = 2;

        // Procesar todas las instrucciones
        for (MiniLangParser.InstruccionContext instruccion : ctx.instruccion()) {
            String code = visit(instruccion);
            if (code != null && !code.trim().isEmpty()) {
                sb.append(indent()).append(code);
                if (!code.trim().endsWith("}") && !code.trim().endsWith("{")) {
                    sb.append(";");
                }
                sb.append("\n");
            }
        }

        // Cerrar main y clase
        sb.append("    }\n");
        sb.append("}\n");

        return sb.toString();
    }

    @Override
    public String visitDeclaracion(MiniLangParser.DeclaracionContext ctx) {
        String varName = ctx.ID().getText();
        String expr = visit(ctx.expr());
        variables.add(varName);

        return "int " + varName + " = " + expr;
    }

    @Override
    public String visitAsignacion(MiniLangParser.AsignacionContext ctx) {
        String varName = ctx.ID().getText();
        String expr = visit(ctx.expr());

        if (!variables.contains(varName)) {
            // Primera vez que se usa esta variable - declararla
            variables.add(varName);
            return "int " + varName + " = " + expr;
        } else {
            // Variable ya declarada - solo asignar
            return varName + " = " + expr;
        }
    }

    @Override
    public String visitImprimir(MiniLangParser.ImprimirContext ctx) {
        String expr = visit(ctx.expr());
        return "System.out.println(" + expr + ")";
    }

    @Override
    public String visitIf(MiniLangParser.IfContext ctx) {
        StringBuilder sb = new StringBuilder();
        String condition = visit(ctx.expr());

        // Generar estructura if con condición booleana
        sb.append("if (").append(condition).append(") {\n");

        // Aumentar indentación para el bloque interno
        indentLevel++;
        for (MiniLangParser.InstruccionContext instruccion : ctx.instruccion()) {
            String code = visit(instruccion);
            if (code != null && !code.trim().isEmpty()) {
                sb.append(indent()).append(code);
                if (!code.trim().endsWith("}") && !code.trim().endsWith("{")) {
                    sb.append(";");
                }
                sb.append("\n");
            }
        }
        indentLevel--;

        sb.append(indent()).append("}");
        return sb.toString();
    }

    @Override
    public String visitWhile(MiniLangParser.WhileContext ctx) {
        StringBuilder sb = new StringBuilder();
        String condition = visit(ctx.expr());

        // Generar estructura while con condición booleana
        sb.append("while (").append(condition).append(") {\n");

        // Aumentar indentación para el bloque interno
        indentLevel++;
        for (MiniLangParser.InstruccionContext instruccion : ctx.instruccion()) {
            String code = visit(instruccion);
            if (code != null && !code.trim().isEmpty()) {
                sb.append(indent()).append(code);
                if (!code.trim().endsWith("}") && !code.trim().endsWith("{")) {
                    sb.append(";");
                }
                sb.append("\n");
            }
        }
        indentLevel--;

        sb.append(indent()).append("}");
        return sb.toString();
    }

    @Override
    public String visitBlankLine(MiniLangParser.BlankLineContext ctx) {
        // Líneas vacías no generan código - simplemente las ignoramos
        return "";
    }

    @Override
    public String visitComparison(MiniLangParser.ComparisonContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        // Los operadores de comparación se traducen directamente
        String javaOp = op;
        switch (op) {
            case "==":
                javaOp = "==";
                break;
            case "!=":
                javaOp = "!=";
                break;
            case ">":
                javaOp = ">";
                break;
            case ">=":
                javaOp = ">=";
                break;
            case "<":
                javaOp = "<";
                break;
            case "<=":
                javaOp = "<=";
                break;
        }

        return "(" + left + " " + javaOp + " " + right + ")";
    }

    @Override
    public String visitMulDiv(MiniLangParser.MulDivContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        return "(" + left + " " + op + " " + right + ")";
    }

    @Override
    public String visitAddSub(MiniLangParser.AddSubContext ctx) {
        String left = visit(ctx.expr(0));
        String right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        return "(" + left + " " + op + " " + right + ")";
    }

    @Override
    public String visitParentesis(MiniLangParser.ParentesisContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }

    @Override
    public String visitLiteral(MiniLangParser.LiteralContext ctx) {
        return ctx.NUMERO().getText();
    }

    @Override
    public String visitVariable(MiniLangParser.VariableContext ctx) {
        return ctx.ID().getText();
    }
}