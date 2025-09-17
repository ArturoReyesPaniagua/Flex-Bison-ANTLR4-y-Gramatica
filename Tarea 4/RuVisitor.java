// Generated from Ru.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RuParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RuVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RuParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(RuParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(RuParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(RuParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(RuParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#imprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprimir(RuParser.ImprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(RuParser.LogContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#sentencia_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia_if(RuParser.Sentencia_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#bloque_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_if(RuParser.Bloque_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#bloque_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_else(RuParser.Bloque_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#sentencia_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia_while(RuParser.Sentencia_whileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RuParser#bloque_while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_while(RuParser.Bloque_whileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(RuParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpr(RuParser.TrueExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(RuParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nilExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNilExpr(RuParser.NilExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(RuParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(RuParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(RuParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatExpr(RuParser.FloatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(RuParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(RuParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code menosUnarioExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMenosUnarioExpr(RuParser.MenosUnarioExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(RuParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpr(RuParser.FalseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(RuParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(RuParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpr(RuParser.EqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(RuParser.AndExprContext ctx);
}