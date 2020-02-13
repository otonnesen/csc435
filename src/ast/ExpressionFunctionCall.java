package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class ExpressionFunctionCall extends Expression {
	private ExpressionIdentifier id;
	private ArrayList<Expression> exprList;

	public ExpressionFunctionCall(ExpressionIdentifier id,
									ArrayList<Expression> exprList) {
		this.id = id;
		this.exprList = exprList;
	}

	public ExpressionIdentifier getId() {
		return this.id;
	}

	public ArrayList<Expression> getExprList() {
		return this.exprList;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
