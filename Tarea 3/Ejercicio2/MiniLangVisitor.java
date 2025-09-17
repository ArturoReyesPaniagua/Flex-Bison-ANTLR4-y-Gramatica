// Generated from MiniLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniLangParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(MiniLangParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Declaracion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(MiniLangParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Asignacion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(MiniLangParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Imprimir}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprimir(MiniLangParser.ImprimirContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(MiniLangParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(MiniLangParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlankLine}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankLine(MiniLangParser.BlankLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MiniLangParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(MiniLangParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(MiniLangParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(MiniLangParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentesis}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentesis(MiniLangParser.ParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Literal}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MiniLangParser.LiteralContext ctx);
}