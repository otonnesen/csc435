package ast;

import visitor.Visitor;

public class StatementPrint extends Statement {
	private Expression e;

	public StatementPrint(Expression e) {
		this.e = e;
	}

	public Expression getExpr() {
		return this.e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
