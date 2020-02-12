package ast;
import visitor.Visitor;

public class ExpressionParenthesis extends Expression {
	private Expression expr;

	public ExpressionParenthesis(Expression expr) {
		this.expr = expr;
	}

	public Expression getExpr() {
		return this.expr;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
