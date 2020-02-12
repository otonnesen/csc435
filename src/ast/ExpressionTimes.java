package ast;
import visitor.Visitor;

public class ExpressionTimes extends ExpressionOperation {
	public ExpressionTimes(Expression left, Expression right) {
		super(left, right);
	}

	public Expression getLeftExpr() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
