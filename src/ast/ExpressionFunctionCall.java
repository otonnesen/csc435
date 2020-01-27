package ast;
import visitor.Visitor;

public class ExpressionFunctionCall extends Expression {
	private ExpressionIdentifier id;
	private ExpressionList exprList;

	public ExpressionFunctionCall(ExpressionIdentifier id, ExpressionList exprList) {
		this.id = id;
		this.exprList = exprList;
	}

	public ExpressionIdentifier getId() {
		return this.id;
	}

	public ExpressionList getExprList() {
		return this.exprList;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
