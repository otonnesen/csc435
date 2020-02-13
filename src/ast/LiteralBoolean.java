package ast;

import visitor.Visitor;

public class LiteralBoolean extends Literal {
	private boolean value;

	public LiteralBoolean(boolean b) {
		this.value = b;
	}

	public boolean getValue() {
		return this.value;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
