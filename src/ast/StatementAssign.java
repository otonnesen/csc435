package ast;

import visitor.Visitor;

public class StatementAssign extends Statement {
	private ExpressionIdentifier id;
	private Expression e;

	public StatementAssign(ExpressionIdentifier id, Expression e) {
		this.id = id;
		this.e = e;
	}

	public ExpressionIdentifier getId() {
		return this.id;
	}

	public Expression getExpr() {
		return this.e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
