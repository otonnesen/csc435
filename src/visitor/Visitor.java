package visitor;
import ast.*;

public abstract class Visitor {
	public abstract Object visit(Block b);
	public abstract Object visit(Declaration d);
	public abstract Object visit(ExpressionArrayAccess e);
	public abstract Object visit(ExpressionFunctionCall e);
	public abstract Object visit(ExpressionIdentifier e);
	public abstract Object visit(ExpressionIsEqual e);
	public abstract Object visit(ExpressionLessThan e);
	public abstract Object visit(ExpressionMinus e);
	public abstract Object visit(ExpressionParenthesis e);
	public abstract Object visit(ExpressionPlus e);
	public abstract Object visit(ExpressionTimes e);
	public abstract Object visit(FunctionBody fb);
	public abstract Object visit(FunctionDeclaration fd);
	public abstract Object visit(Function f);
	public abstract Object visit(LiteralBoolean b);
	public abstract Object visit(LiteralCharacter c);
	public abstract Object visit(LiteralFloat f);
	public abstract Object visit(LiteralInteger i);
	public abstract Object visit(LiteralString s);
	public abstract Object visit(Program p);
	public abstract Object visit(StatementArrayAssignment s);
	public abstract Object visit(StatementAssign s);
	public abstract Object visit(StatementEmpty s);
	public abstract Object visit(StatementExpression s);
	public abstract Object visit(StatementIf s);
	public abstract Object visit(StatementPrint s);
	public abstract Object visit(StatementPrintln s);
	public abstract Object visit(StatementReturn s);
	public abstract Object visit(StatementWhile s);
	public abstract Object visit(VariableDeclaration v);
}
