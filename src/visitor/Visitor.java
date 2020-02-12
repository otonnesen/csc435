package visitor;
import ast.*;

public abstract class Visitor<T> {
	public abstract T visit(Block b);
	public abstract T visit(Declaration d);
	public abstract T visit(ExpressionArrayAccess e);
	public abstract T visit(ExpressionFunctionCall e);
	public abstract T visit(ExpressionIdentifier e);
	public abstract T visit(ExpressionIsEqual e);
	public abstract T visit(ExpressionLessThan e);
	public abstract T visit(ExpressionMinus e);
	public abstract T visit(ExpressionParenthesis e);
	public abstract T visit(ExpressionPlus e);
	public abstract T visit(ExpressionTimes e);
	public abstract T visit(FunctionBody fb);
	public abstract T visit(FunctionDeclaration fd);
	public abstract T visit(Function f);
	public abstract T visit(LiteralBoolean b);
	public abstract T visit(LiteralCharacter c);
	public abstract T visit(LiteralFloat f);
	public abstract T visit(LiteralInteger i);
	public abstract T visit(LiteralString s);
	public abstract T visit(Program p);
	public abstract T visit(StatementArrayAssignment s);
	public abstract T visit(StatementAssign s);
	public abstract T visit(StatementEmpty s);
	public abstract T visit(StatementExpression s);
	public abstract T visit(StatementIf s);
	public abstract T visit(StatementPrint s);
	public abstract T visit(StatementPrintln s);
	public abstract T visit(StatementReturn s);
	public abstract T visit(StatementWhile s);
	public abstract T visit(VariableDeclaration v);
}
