package ast;

import visitor.Visitor;

public class StatementPrintln extends Statement {
	private Expression e;

	public StatementPrintln(Expression e) {
		this.e = e;
	}

	public Expression getExpr() {
		return this.e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
