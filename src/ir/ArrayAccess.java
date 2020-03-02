package ir;

import type.*;

public class ArrayAccess extends Operand {
	private Temp id;
	private Temp index;

	public ArrayAccess(Temp id, Temp index) {
		super(id.getType());
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
}
