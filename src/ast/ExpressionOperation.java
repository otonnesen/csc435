package ast;

public abstract class ExpressionOperation extends Expression {
	private Expression left;
	private Expression right;
	protected String op;

	public ExpressionOperation(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeftExpr() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public String toString() {
		return this.op;
	}
}
