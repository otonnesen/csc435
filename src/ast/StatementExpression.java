package ast;

import visitor.Visitor;

public class StatementExpression extends Statement {
	private Expression e;

	public StatementExpression(Expression e) {
		this.e = e;
	}

	public Expression getExpr() {
		return this.e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
