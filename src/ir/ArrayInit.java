package ir;

import codegen.JasminVisitor;
import type.Type;

public class ArrayInit extends Operand {
	private int size;

	public ArrayInit(Type type, int size) {
		super(type);
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public String toString() {
		// return "NEWARRAY (" + this.type.getAtomicType().toString() + ")" + this.size;
		// Turns out the spec was wrong :|
		return "NEWARRAY " + this.type.getAtomicType().toString() + this.size;
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
