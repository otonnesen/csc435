package ast;
import visitor.Visitor;

public class ExpressionPlusMinus extends ExpressionOperation {
	public ExpressionPlusMinus(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String toString() {
		return this.e1 + " +- " + this.e2;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
