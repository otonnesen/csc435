package ir;

import codegen.JasminVisitor;
import type.Type;

public class ArrayAccess extends Operand {
	private Temp id;
	private Temp index;

	public ArrayAccess(Temp id, Temp index) {
		super(new Type(id.getType().getAtomicType()));
		this.id = id;
		this.index = index;
	}

	public Temp getId() {
		return this.id;
	}

	public Temp getIndex() {
		return this.index;
	}

	public String toString() {
		return this.id.toString() + "[" + this.index.toString() + "]";
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
