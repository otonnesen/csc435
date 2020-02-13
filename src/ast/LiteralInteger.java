package ast;

import visitor.Visitor;

public class LiteralInteger extends Literal {
	private int value;

	public LiteralInteger(int i) {
		this.value = i;
	}

	public int getValue() {
		return this.value;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
