package ast;
import visitor.Visitor;

public class ExpressionIsEqual extends ExpressionOperation {
	public ExpressionIsEqual(Expression left, Expression right) {
		super(left, right);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
