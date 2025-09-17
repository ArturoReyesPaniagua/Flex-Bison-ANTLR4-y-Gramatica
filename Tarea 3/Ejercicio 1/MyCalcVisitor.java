import java.util.HashMap;
import java.util.Map;

public class MyCalcVisitor extends CalcBaseVisitor<Double> {

    // Tabla de símbolos para almacenar variables
    private Map<String, Double> variables = new HashMap<>();

    @Override
    public Double visitProg(CalcParser.ProgContext ctx) {
        return visit(ctx.stat());
    }

    @Override
    public Double visitAssign(CalcParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        Double value = visit(ctx.expr());
        variables.put(varName, value);
        System.out.println(varName + " = " + value); // Confirmar asignación
        return value;
    }

    @Override
    public Double visitPrintExpr(CalcParser.PrintExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitPowerExpr(CalcParser.PowerExprContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        return Math.pow(left, right);
    }

    @Override
    public Double visitSinExpr(CalcParser.SinExprContext ctx) {
        Double value = visit(ctx.expr());
        return Math.sin(value);
    }

    @Override
    public Double visitCosExpr(CalcParser.CosExprContext ctx) {
        Double value = visit(ctx.expr());
        return Math.cos(value);
    }

    @Override
    public Double visitLogExpr(CalcParser.LogExprContext ctx) {
        Double value = visit(ctx.expr());
        if (value <= 0) {
            throw new RuntimeException("Error: log de número no positivo: " + value);
        }
        return Math.log10(value);
    }

    @Override
    public Double visitMulExpr(CalcParser.MulExprContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        return left * right;
    }

    @Override
    public Double visitDivExpr(CalcParser.DivExprContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        if (right == 0) {
            throw new RuntimeException("Error: División por cero");
        }
        return left / right;
    }

    @Override
    public Double visitAddExpr(CalcParser.AddExprContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        return left + right;
    }

    @Override
    public Double visitSubExpr(CalcParser.SubExprContext ctx) {
        Double left = visit(ctx.expr(0));
        Double right = visit(ctx.expr(1));
        return left - right;
    }

    @Override
    public Double visitParenExpr(CalcParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitNumberExpr(CalcParser.NumberExprContext ctx) {
        return Double.valueOf(ctx.NUM().getText());
    }

    @Override
    public Double visitVarExpr(CalcParser.VarExprContext ctx) {
        String varName = ctx.ID().getText();
        if (variables.containsKey(varName)) {
            return variables.get(varName);
        } else {
            throw new RuntimeException("Variable no definida: " + varName);
        }
    }

    // Método para obtener las variables
    public Map<String, Double> getVariables() {
        return new HashMap<>(variables);
    }
}