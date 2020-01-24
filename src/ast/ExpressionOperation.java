package ast;

public class ExpressionOperation extends Expression {
	protected Expression e1;
	protected Expression e2;

	public ExpressionOperation(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
}
