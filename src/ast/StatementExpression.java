package ast;

public class StatementExpression extends Statement {
	private Expression e;

	public StatementExpression(Expression e) {
		this.e = e;
	}
}
