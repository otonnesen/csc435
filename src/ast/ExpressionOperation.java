package ast;

public abstract class ExpressionOperation extends Expression {
	protected Expression left;
	protected Expression right;

	public ExpressionOperation(int line, int offset, Expression left, Expression right) {
		super(line, offset);
		this.left = left;
		this.right = right;
	}
}
