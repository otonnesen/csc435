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

	public Expression getExpression() {
		return this.e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
