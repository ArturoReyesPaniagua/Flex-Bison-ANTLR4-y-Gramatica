// Generated from Ru.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuParser}.
 */
public interface RuListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(RuParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(RuParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(RuParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(RuParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(RuParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(RuParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(RuParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(RuParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void enterImprimir(RuParser.ImprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void exitImprimir(RuParser.ImprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(RuParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(RuParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#sentencia_if}.
	 * @param ctx the parse tree
	 */
	void enterSentencia_if(RuParser.Sentencia_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#sentencia_if}.
	 * @param ctx the parse tree
	 */
	void exitSentencia_if(RuParser.Sentencia_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#bloque_if}.
	 * @param ctx the parse tree
	 */
	void enterBloque_if(RuParser.Bloque_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#bloque_if}.
	 * @param ctx the parse tree
	 */
	void exitBloque_if(RuParser.Bloque_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#bloque_else}.
	 * @param ctx the parse tree
	 */
	void enterBloque_else(RuParser.Bloque_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#bloque_else}.
	 * @param ctx the parse tree
	 */
	void exitBloque_else(RuParser.Bloque_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#sentencia_while}.
	 * @param ctx the parse tree
	 */
	void enterSentencia_while(RuParser.Sentencia_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#sentencia_while}.
	 * @param ctx the parse tree
	 */
	void exitSentencia_while(RuParser.Sentencia_whileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuParser#bloque_while}.
	 * @param ctx the parse tree
	 */
	void enterBloque_while(RuParser.Bloque_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuParser#bloque_while}.
	 * @param ctx the parse tree
	 */
	void exitBloque_while(RuParser.Bloque_whileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(RuParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(RuParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(RuParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(RuParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(RuParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(RuParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nilExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNilExpr(RuParser.NilExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nilExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNilExpr(RuParser.NilExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(RuParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(RuParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(RuParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(RuParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(RuParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(RuParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(RuParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(RuParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(RuParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(RuParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(RuParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(RuParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code menosUnarioExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMenosUnarioExpr(RuParser.MenosUnarioExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code menosUnarioExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMenosUnarioExpr(RuParser.MenosUnarioExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(RuParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(RuParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(RuParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(RuParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(RuParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(RuParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(RuParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(RuParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(RuParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(RuParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(RuParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(RuParser.AndExprContext ctx);
}