package ir;

import type.Type;

public abstract class Operand {
	protected Type type;

	public Operand(Type t) {
		this.type = t;
	}

	public Type getType() {
		return this.type;
	}
}