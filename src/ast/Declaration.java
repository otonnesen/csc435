package ast;

import visitor.Visitor;
import type.Type;

public class Declaration {
	private Type type;
	private ExpressionIdentifier id;

	public Declaration(Type type, ExpressionIdentifier id) {
		this.type = type;
		this.id = id;
	}

	public Type getType() {
		return this.type;
	}

	public ExpressionIdentifier getId() {
		return this.id;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
