package ast;
import java.util.ArrayList;

public class ExpressionList extends ASTNode {
	private ArrayList expressions;

	public ExpressionList() {
		this.expressions = new ArrayList<Expression>();
	}

	public void addExpression(Expression e) {
		this.expressions.add(e);
	}
}
