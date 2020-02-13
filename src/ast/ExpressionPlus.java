package ast;
import visitor.Visitor;

public class ExpressionPlus extends ExpressionOperation {
	public ExpressionPlus(Expression left, Expression right) {
		super(left, right);
		this.op = "+";
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
