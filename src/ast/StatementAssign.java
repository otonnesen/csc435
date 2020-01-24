package ast;

public class StatementAssign extends Statement {
	private ExpressionIdentifier id;
	private Expression e;

	public StatementAssign(ExpressionIdentifier id, Expression e) {
		this.id = id;
		this.e = e;
	}
}
