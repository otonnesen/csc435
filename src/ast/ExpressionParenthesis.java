package ast;

public class ExpressionParenthesis extends Expression {
	private Expression expr;

	public ExpressionParenthesis(Expression expr) {
		this.expr = expr;
	}
}
