package ast;

import visitor.Visitor;

public class LiteralFloat extends Literal {
	private float value;

	public LiteralFloat(float f) {
		this.value = f;
	}

	public float getValue() {
		return this.value;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
