package ast;
import visitor.Visitor;

public class ExpressionTimes extends ExpressionOperation {
	public ExpressionTimes(Expression left, Expression right) {
		super(left, right);
	}

	public Expression getLeft() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public String toString() {
		return this.left + " * " + this.right;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
