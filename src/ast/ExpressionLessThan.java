package ast;
import visitor.Visitor;

public class ExpressionLessThan extends ExpressionOperation {
	public ExpressionLessThan(Expression left, Expression right) {
		super(left, right, "<");
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
