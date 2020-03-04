package ir;

import type.Type;

public class ArrayInit extends Operand {
	private int size;

	public ArrayInit(Type type, int size) {
		super(type);
		this.size = size;
	}

	public String toString() {
		// return "NEWARRAY (" + this.type.getAtomicType().toString() + ")" + this.size;
		// Turns out the spec was wrong :|
		return "NEWARRAY " + this.type.getAtomicType().toString() + this.size;
	}
}
