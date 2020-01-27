package ast;
import visitor.Visitor;
import java.util.ArrayList;

public class ExpressionList extends ASTNode {
	private ArrayList<Expression> expressions;

	public ExpressionList() {
		this.expressions = new ArrayList<Expression>();
	}

	public void addExpression(Expression e) {
		this.expressions.add(e);
	}

	public ArrayList<Expression> getExpressions() {
		return this.expressions;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
