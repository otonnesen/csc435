package ast;

import visitor.Visitor;

public class ExpressionTimes extends ExpressionOperation {
	public static final String op = "*";

	public ExpressionTimes(Expression left, Expression right) {
		super(left, right, op);
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
