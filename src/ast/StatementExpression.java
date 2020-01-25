package ast;
import visitor.Visitor;

public class StatementExpression extends Statement {
	private Expression e;

	public StatementExpression(Expression e) {
		this.e = e;
	}

	public Expression getExpression() {
		return this.e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
