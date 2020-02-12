package ast;
import visitor.Visitor;

public class StatementReturn extends Statement {
	private Expression e;

	public StatementReturn(Expression e) {
		this.e = e;
	}

	public Expression getExpr() {
		return this.e;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
