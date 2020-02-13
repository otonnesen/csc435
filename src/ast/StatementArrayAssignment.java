package ast;

import visitor.Visitor;

public class StatementArrayAssignment extends Statement {
	private ExpressionArrayAccess eaa;
	private Expression e;

	public StatementArrayAssignment(ExpressionArrayAccess eaa, Expression e) {
		this.eaa = eaa;
		this.e = e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	public ExpressionArrayAccess getArrayAccess() {
		return this.eaa;
	}

	public Expression getExpr() {
		return this.e;
	}
}
