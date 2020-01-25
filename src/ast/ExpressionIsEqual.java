package ast;
import visitor.Visitor;

public class ExpressionIsEqual extends ExpressionOperation {
	public ExpressionIsEqual(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
