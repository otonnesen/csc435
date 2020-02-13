package ast;
import visitor.Visitor;

public class ExpressionMinus extends ExpressionOperation {
	public ExpressionMinus(Expression left, Expression right) {
		super(left, right, "-");
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
