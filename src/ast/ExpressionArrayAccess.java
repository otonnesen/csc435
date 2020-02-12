package ast;
import visitor.Visitor;

public class ExpressionArrayAccess extends Expression {
	private ExpressionIdentifier id;
	private Expression e;

	public ExpressionArrayAccess(ExpressionIdentifier id, Expression e) {
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
