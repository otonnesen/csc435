package ast;
import visitor.Visitor;

public class ExpressionLessThan extends ExpressionOperation {
	public ExpressionLessThan(Expression left, Expression right) {
		super(left, right);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
