package ast;

public class ExpressionArrayAccess extends Expression {
	private ExpressionIdentifier id;
	private Expression e;

	public ExpressionArrayAccess(ExpressionIdentifier id, Expression e) {
		this.id = id;
		this.e = e;
	}
}
