package ast;

import visitor.Visitor;

import java.util.ArrayList;

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

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
