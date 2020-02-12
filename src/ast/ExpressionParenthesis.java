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

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
