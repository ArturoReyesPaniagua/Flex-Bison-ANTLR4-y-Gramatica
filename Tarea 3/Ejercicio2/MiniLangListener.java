// Generated from MiniLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniLangParser}.
 */
public interface MiniLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniLangParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(MiniLangParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniLangParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(MiniLangParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaracion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(MiniLangParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaracion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(MiniLangParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Asignacion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(MiniLangParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Asignacion}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(MiniLangParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Imprimir}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterImprimir(MiniLangParser.ImprimirContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Imprimir}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitImprimir(MiniLangParser.ImprimirContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterIf(MiniLangParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitIf(MiniLangParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterWhile(MiniLangParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitWhile(MiniLangParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlankLine}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterBlankLine(MiniLangParser.BlankLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlankLine}
	 * labeled alternative in {@link MiniLangParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitBlankLine(MiniLangParser.BlankLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MiniLangParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MiniLangParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparison(MiniLangParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparison(MiniLangParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(MiniLangParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(MiniLangParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(MiniLangParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(MiniLangParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parentesis}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParentesis(MiniLangParser.ParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parentesis}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParentesis(MiniLangParser.ParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Literal}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MiniLangParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Literal}
	 * labeled alternative in {@link MiniLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MiniLangParser.LiteralContext ctx);
}