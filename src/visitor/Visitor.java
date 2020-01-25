package visitor;
import ast.*;

public interface Visitor {
	public Object visit(Block b);
	public Object visit(Declaration d);
	public Object visit(ExpressionArrayAccess e);
	public Object visit(ExpressionFunctionCall e);
	public Object visit(ExpressionIdentifier e);
	public Object visit(ExpressionIsEqual e);
	public Object visit(ExpressionLessThan e);
	public Object visit(ExpressionList el);
	public Object visit(ExpressionParenthesis e);
	public Object visit(ExpressionPlusMinus e);
	public Object visit(ExpressionTimes e);
	public Object visit(FunctionBody fb);
	public Object visit(FunctionDeclaration fd);
	public Object visit(Function f);
	public Object visit(LiteralBoolean b);
	public Object visit(LiteralCharacter c);
	public Object visit(LiteralFloat f);
	public Object visit(LiteralInteger i);
	public Object visit(LiteralString s);
	public Object visit(Program p);
	public Object visit(StatementArrayAssignment s);
	public Object visit(StatementAssign s);
	public Object visit(StatementEmpty s);
	public Object visit(StatementExpression s);
	public Object visit(StatementIf s);
	public Object visit(StatementPrint s);
	public Object visit(StatementPrintln s);
	public Object visit(StatementReturn s);
	public Object visit(StatementWhile s);
	public Object visit(VariableDeclaration v);
}
