import java.util.*;
import java.io.*;

public class RuInterpreter extends RuBaseVisitor<Object> {

    // Tabla de símbolos para variables
    private Map<String, Object> variables = new HashMap<>();

    // Buffer para capturar la salida
    private StringBuilder output = new StringBuilder();

    // Buffer para capturar logs
    private StringBuilder logOutput = new StringBuilder();

    // Stack para manejo de scope (futuras extensiones)
    private Stack<Map<String, Object>> scopes = new Stack<>();

    public RuInterpreter() {
        // Inicializar scope global
        scopes.push(variables);
    }

    // Métodos para obtener la salida
    public String getOutput() {
        return output.toString();
    }

    public String getLogOutput() {
        return logOutput.toString();
    }

    public void clearOutput() {
        output.setLength(0);
        logOutput.setLength(0);
    }

    @Override
    public Object visitPrograma(RuParser.ProgramaContext ctx) {
        try {
            return visit(ctx.bloque());
        } catch (Exception e) {
            throw new RuntimeException("Error durante la ejecución: " + e.getMessage(), e);
        }
    }

    @Override
    public Object visitBloque(RuParser.BloqueContext ctx) {
        Object result = null;
        for (RuParser.SentenciaContext sentencia : ctx.sentencia()) {
            result = visit(sentencia);
        }
        return result;
    }

    @Override
    public Object visitSentencia(RuParser.SentenciaContext ctx) {
        if (ctx.asignacion() != null) {
            return visit(ctx.asignacion());
        } else if (ctx.sentencia_if() != null) {
            return visit(ctx.sentencia_if());
        } else if (ctx.sentencia_while() != null) {
            return visit(ctx.sentencia_while());
        } else if (ctx.log() != null) {
            return visit(ctx.log());
        } else if (ctx.imprimir() != null) {
            return visit(ctx.imprimir());
        }
        return null;
    }

    @Override
    public Object visitAsignacion(RuParser.AsignacionContext ctx) {
        String varName = ctx.ID().getText();
        Object value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Object visitSentencia_if(RuParser.Sentencia_ifContext ctx) {
        // Evaluar condición principal
        Object condition = visit(ctx.bloque_condicional(0).expr());
        if (isTrue(condition)) {
            return visit(ctx.bloque_condicional(0).bloque_de_sentencia());
        }

        // Evaluar else if
        for (int i = 1; i < ctx.bloque_condicional().size(); i++) {
            condition = visit(ctx.bloque_condicional(i).expr());
            if (isTrue(condition)) {
                return visit(ctx.bloque_condicional(i).bloque_de_sentencia());
            }
        }

        // Evaluar else
        if (ctx.bloque_de_sentencia() != null) {
            return visit(ctx.bloque_de_sentencia());
        }

        return null;
    }

    @Override
    public Object visitBloque_de_sentencia(RuParser.Bloque_de_sentenciaContext ctx) {
        if (ctx.bloque() != null) {
            return visit(ctx.bloque());
        } else if (ctx.sentencia() != null) {
            return visit(ctx.sentencia());
        }
        return null;
    }

    @Override
    public Object visitSentencia_while(RuParser.Sentencia_whileContext ctx) {
        Object result = null;
        while (true) {
            Object condition = visit(ctx.expr());
            if (!isTrue(condition)) {
                break;
            }
            result = visit(ctx.bloque_de_sentencia());
        }
        return result;
    }

    @Override
    public Object visitLog(RuParser.LogContext ctx) {
        Object value = visit(ctx.expr());
        String logMessage = "[LOG] " + formatValue(value);
        logOutput.append(logMessage).append("\n");
        System.out.println(logMessage); // También imprimir en consola
        return value;
    }

    @Override
    public Object visitImprimir(RuParser.ImprimirContext ctx) {
        Object value = visit(ctx.expr());
        String message = formatValue(value);
        output.append(message).append("\n");
        return value;
    }

    // Expresiones
    @Override
    public Object visitPowExpr(RuParser.PowExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        return Math.pow(toDouble(left), toDouble(right));
    }

    @Override
    public Object visitMenosUnarioExpr(RuParser.MenosUnarioExprContext ctx) {
        Object value = visit(ctx.expr());
        if (value instanceof Integer) {
            return -(Integer) value;
        } else if (value instanceof Double) {
            return -(Double) value;
        }
        throw new RuntimeException("Operador menos unario no aplicable a: " + value);
    }

    @Override
    public Object visitNotExpr(RuParser.NotExprContext ctx) {
        Object value = visit(ctx.expr());
        return !isTrue(value);
    }

    @Override
    public Object visitMultiplicacionExpr(RuParser.MultiplicacionExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        switch (op) {
            case "*":
                return multiply(left, right);
            case "/":
                return divide(left, right);
            case "%":
                return modulo(left, right);
            default:
                throw new RuntimeException("Operador desconocido: " + op);
        }
    }

    @Override
    public Object visitAditivaExpr(RuParser.AditivaExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        switch (op) {
            case "+":
                return add(left, right);
            case "-":
                return subtract(left, right);
            default:
                throw new RuntimeException("Operador desconocido: " + op);
        }
    }

    @Override
    public Object visitRelacionalExpr(RuParser.RelacionalExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        switch (op) {
            case ">":
                return compare(left, right) > 0;
            case "<":
                return compare(left, right) < 0;
            case ">=":
                return compare(left, right) >= 0;
            case "<=":
                return compare(left, right) <= 0;
            default:
                throw new RuntimeException("Operador desconocido: " + op);
        }
    }

    @Override
    public Object visitIgualdadExpr(RuParser.IgualdadExprContext ctx) {
        Object left = visit(ctx.expr(0));
        Object right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        switch (op) {
            case "==":
                return equals(left, right);
            case "!=":
                return !equals(left, right);
            default:
                throw new RuntimeException("Operador desconocido: " + op);
        }
    }

    @Override
    public Object visitAndExpr(RuParser.AndExprContext ctx) {
        Object left = visit(ctx.expr(0));
        if (!isTrue(left)) {
            return false; // Short-circuit evaluation
        }
        Object right = visit(ctx.expr(1));
        return isTrue(right);
    }

    @Override
    public Object visitOrExpr(RuParser.OrExprContext ctx) {
        Object left = visit(ctx.expr(0));
        if (isTrue(left)) {
            return true; // Short-circuit evaluation
        }
        Object right = visit(ctx.expr(1));
        return isTrue(right);
    }

    @Override
    public Object visitAtomExpr(RuParser.AtomExprContext ctx) {
        return visit(ctx.atomo());
    }

    // Átomos
    @Override
    public Object visitParExpr(RuParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitNumberAtom(RuParser.NumberAtomContext ctx) {
        if (ctx.INT() != null) {
            return Integer.parseInt(ctx.INT().getText());
        } else if (ctx.FLOAT() != null) {
            return Double.parseDouble(ctx.FLOAT().getText());
        }
        throw new RuntimeException("Número inválido");
    }

    @Override
    public Object visitBooleanAtom(RuParser.BooleanAtomContext ctx) {
        if (ctx.TRUE() != null) {
            return true;
        } else if (ctx.FALSE() != null) {
            return false;
        }
        throw new RuntimeException("Valor booleano inválido");
    }

    @Override
    public Object visitIdAtom(RuParser.IdAtomContext ctx) {
        String varName = ctx.ID().getText();
        if (variables.containsKey(varName)) {
            return variables.get(varName);
        }
        throw new RuntimeException("Variable no definida: " + varName);
    }

    @Override
    public Object visitStringAtom(RuParser.StringAtomContext ctx) {
        String text = ctx.STRING().getText();
        // Remover comillas y procesar escapes
        return text.substring(1, text.length() - 1).replace("\"\"", "\"");
    }

    @Override
    public Object visitNilAtom(RuParser.NilAtomContext ctx) {
        return null;
    }

    // Métodos auxiliares
    private boolean isTrue(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Boolean) return (Boolean) obj;
        if (obj instanceof Integer) return (Integer) obj != 0;
        if (obj instanceof Double) return (Double) obj != 0.0;
        if (obj instanceof String) return !((String) obj).isEmpty();
        return true;
    }

    private double toDouble(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        } else if (obj instanceof Double) {
            return (Double) obj;
        }
        throw new RuntimeException("No se puede convertir a número: " + obj);
    }

    private Object add(Object left, Object right) {
        // Concatenación de strings
        if (left instanceof String || right instanceof String) {
            return formatValue(left) + formatValue(right);
        }

        // Suma numérica
        if (isNumeric(left) && isNumeric(right)) {
            if (left instanceof Integer && right instanceof Integer) {
                return (Integer) left + (Integer) right;
            } else {
                return toDouble(left) + toDouble(right);
            }
        }

        throw new RuntimeException("No se puede sumar: " + left + " + " + right);
    }

    private Object subtract(Object left, Object right) {
        if (isNumeric(left) && isNumeric(right)) {
            if (left instanceof Integer && right instanceof Integer) {
                return (Integer) left - (Integer) right;
            } else {
                return toDouble(left) - toDouble(right);
            }
        }
        throw new RuntimeException("No se puede restar: " + left + " - " + right);
    }

    private Object multiply(Object left, Object right) {
        if (isNumeric(left) && isNumeric(right)) {
            if (left instanceof Integer && right instanceof Integer) {
                return (Integer) left * (Integer) right;
            } else {
                return toDouble(left) * toDouble(right);
            }
        }
        throw new RuntimeException("No se puede multiplicar: " + left + " * " + right);
    }

    private Object divide(Object left, Object right) {
        if (isNumeric(left) && isNumeric(right)) {
            double rightVal = toDouble(right);
            if (rightVal == 0) {
                throw new RuntimeException("División por cero");
            }
            return toDouble(left) / rightVal;
        }
        throw new RuntimeException("No se puede dividir: " + left + " / " + right);
    }

    private Object modulo(Object left, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            int rightVal = (Integer) right;
            if (rightVal == 0) {
                throw new RuntimeException("Módulo por cero");
            }
            return (Integer) left % rightVal;
        }
        throw new RuntimeException("Operador módulo solo para enteros: " + left + " % " + right);
    }

    private int compare(Object left, Object right) {
        if (isNumeric(left) && isNumeric(right)) {
            return Double.compare(toDouble(left), toDouble(right));
        }
        if (left instanceof String && right instanceof String) {
            return ((String) left).compareTo((String) right);
        }
        throw new RuntimeException("No se puede comparar: " + left + " con " + right);
    }

    private boolean equals(Object left, Object right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (isNumeric(left) && isNumeric(right)) {
            return Double.compare(toDouble(left), toDouble(right)) == 0;
        }

        return left.equals(right);
    }

    private boolean isNumeric(Object obj) {
        return obj instanceof Integer || obj instanceof Double;
    }

    private String formatValue(Object obj) {
        if (obj == null) return "nil";
        if (obj instanceof String) return (String) obj;
        if (obj instanceof Boolean) return obj.toString();
        if (obj instanceof Integer) return obj.toString();
        if (obj instanceof Double) {
            // Formatear doubles para mostrar enteros sin decimal si es posible
            double d = (Double) obj;
            if (d == Math.floor(d) && Double.isFinite(d)) {
                return String.valueOf((long) d);
            } else {
                return obj.toString();
            }
        }
        return obj.toString();
    }

    // Método para obtener todas las variables (para debugging)
    public Map<String, Object> getVariables() {
        return new HashMap<>(variables);
    }
}