package ast;
import visitor.Visitor;

public class ExpressionPlusMinus extends ExpressionOperation {
	public ExpressionPlusMinus(Expression left, Expression right) {
		super(left, right);
	}

	public Expression getLeftExpr() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
