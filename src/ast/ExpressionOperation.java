package ast;

public abstract class ExpressionOperation extends Expression {
	protected Expression left;
	protected Expression right;

	public ExpressionOperation(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
}
