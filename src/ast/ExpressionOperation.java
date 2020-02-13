package ast;

public abstract class ExpressionOperation extends Expression {
	private Expression left;
	private Expression right;
	private String op;

	public ExpressionOperation(Expression left, Expression right,
			String op) {
		this.left = left;
		this.right = right;
		this.op = op;
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
