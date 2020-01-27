package ast;
import visitor.Visitor;

public class ExpressionLessThan extends ExpressionOperation {
	public ExpressionLessThan(Expression left, Expression right) {
		super(left, right);
	}

	public Expression getLeft() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
