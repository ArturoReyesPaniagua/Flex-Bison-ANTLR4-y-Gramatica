import java.util.*;

public class MyVisitor extends RuBaseVisitor<Object> {

    // Tabla de variables
    private Map<String, Object> variables = new HashMap<>();

    // Salida del programa
    private StringBuilder output = new StringBuilder();

    // Logs del programa
    private StringBuilder logOutput = new StringBuilder();

    // Componente padre para dialogs
    private java.awt.Component parentComponent = null;

    public MyVisitor() {
        // Constructor simple
    }

    public void setParentComponent(java.awt.Component parent) {
        this.parentComponent = parent;
    }

    public String getOutput() {
        return output.toString();
    }

    public String getLogOutput() {
        return logOutput.toString();
    }

    public void clearOutput() {
        output.setLength(0);
        logOutput.setLength(0);
        variables.clear();
    }

    @Override
    public Object visitPrograma(RuParser.ProgramaContext ctx) {
        // Visitar todas las sentencias
        Object result = null;
        for (RuParser.SentenciaContext sentencia : ctx.sentencia()) {
            result = visit(sentencia);
        }
        return result;
    }

    @Override
    public Object visitSentencia(RuParser.SentenciaContext ctx) {
        if (ctx.declaracion() != null) {
            return visit(ctx.declaracion());
        } else if (ctx.asignacion() != null) {
            return visit(ctx.asignacion());
        } else if (ctx.imprimir() != null) {
            return visit(ctx.imprimir());
        } else if (ctx.log() != null) {
            return visit(ctx.log());
        } else if (ctx.sentencia_if() != null) {
            return visit(ctx.sentencia_if());
        } else if (ctx.sentencia_while() != null) {
            return visit(ctx.sentencia_while());
        }
        return null;
    }

    @Override
    public Object visitDeclaracion(RuParser.DeclaracionContext ctx) {
        String varName = ctx.ID().getText();
        Object value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Object visitAsignacion(RuParser.AsignacionContext ctx) {
        String varName = ctx.ID().getText();
        Object value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Object visitImprimir(RuParser.ImprimirContext ctx) {
        Object value = visit(ctx.expr());
        String message = valueToString(value);
        output.append(message).append("\n");
        return value;
    }

    @Override
    public Object visitLog(RuParser.LogContext ctx) {
        Object value = visit(ctx.expr());
        String message = "[LOG] " + valueToString(value);
        logOutput.append(message).append("\n");
        return value;
    }

    @Override
    public Object visitSentencia_if(RuParser.Sentencia_ifContext ctx) {
        Object condition = visit(ctx.expr());
        if (isTrue(condition)) {
            return visit(ctx.bloque_if());
        } else if (ctx.bloque_else() != null) {
            return visit(ctx.bloque_else());
        }
        return null;
    }

    @Override
    public Object visitBloque_if(RuParser.Bloque_ifContext ctx) {
        Object result = null;
        for (RuParser.SentenciaContext sentencia : ctx.sentencia()) {
            result = visit(sentencia);
        }
        return result;
    }

    @Override
    public Object visitBloque_else(RuParser.Bloque_elseContext ctx) {
        Object result = null;
        for (RuParser.SentenciaContext sentencia : ctx.sentencia()) {
            result = visit(sentencia);
        }
        return result;
    }

    @Override
    public Object visitSentencia_while(RuParser.Sentencia_whileContext ctx) {
        Object result = null;
        while (true) {
            Object condition = visit(ctx.expr());
            if (!isTrue(condition)) break;
            result = visit(ctx.bloque_while());
        }
        return result;
    }

    @Override
    public Object visitBloque_while(RuParser.Bloque_whileContext ctx) {
        Object result = null;
        for (RuParser.SentenciaContext sentencia : ctx.sentencia()) {
            result = visit(sentencia);
        }
        return result;
    }

    // === EXPRESIONES ===

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
        throw new RuntimeException("Operador - no aplicable a: " + value);
    }

    @Override
    public Object visitNotExpr(RuParser.NotExprContext ctx) {
        Object value = visit(ctx.expr());
        return !isTrue(value);
    }

    @Override
    public Object visitMultExpr(RuParser.MultExprContext ctx) {
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
    public Object visitAddExpr(RuParser.AddExprContext ctx) {
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
    public Object visitCompExpr(RuParser.CompExprContext ctx) {
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
    public Object visitEqualExpr(RuParser.EqualExprContext ctx) {
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
        if (!isTrue(left)) return false;
        Object right = visit(ctx.expr(1));
        return isTrue(right);
    }

    @Override
    public Object visitOrExpr(RuParser.OrExprContext ctx) {
        Object left = visit(ctx.expr(0));
        if (isTrue(left)) return true;
        Object right = visit(ctx.expr(1));
        return isTrue(right);
    }

    @Override
    public Object visitParExpr(RuParser.ParExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitIdExpr(RuParser.IdExprContext ctx) {
        String varName = ctx.ID().getText();
        if (variables.containsKey(varName)) {
            return variables.get(varName);
        }
        throw new RuntimeException("Variable no definida: " + varName);
    }

    @Override
    public Object visitIntExpr(RuParser.IntExprContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Object visitFloatExpr(RuParser.FloatExprContext ctx) {
        return Double.parseDouble(ctx.FLOAT().getText());
    }

    @Override
    public Object visitStringExpr(RuParser.StringExprContext ctx) {
        String text = ctx.STRING().getText();
        // Quitar comillas
        return text.substring(1, text.length() - 1);
    }

    @Override
    public Object visitTrueExpr(RuParser.TrueExprContext ctx) {
        return true;
    }

    @Override
    public Object visitFalseExpr(RuParser.FalseExprContext ctx) {
        return false;
    }

    @Override
    public Object visitNilExpr(RuParser.NilExprContext ctx) {
        return null;
    }

    // === MÉTODOS AUXILIARES ===

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
            return valueToString(left) + valueToString(right);
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
        throw new RuntimeException("Operador % solo para enteros");
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

    private String valueToString(Object obj) {
        if (obj == null) return "nil";
        if (obj instanceof String) return (String) obj;
        if (obj instanceof Double) {
            double d = (Double) obj;
            if (d == Math.floor(d) && Double.isFinite(d)) {
                return String.valueOf((long) d);
            }
        }
        return obj.toString();
    }
}