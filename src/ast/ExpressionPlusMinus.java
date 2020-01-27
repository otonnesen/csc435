package ast;
import visitor.Visitor;

public class ExpressionPlusMinus extends ExpressionOperation {
	public ExpressionPlusMinus(Expression left, Expression right) {
		super(left, right);
	}

	public String toString() {
		return this.left + " +- " + this.right;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
