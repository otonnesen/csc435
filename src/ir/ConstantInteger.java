package ir;

import type.Type;
import type.AtomicType;

public class ConstantInteger extends Constant {
	private int value;

	public ConstantInteger(int v) {
		super(new Type(AtomicType.INTEGER));
		this.value = v;
	}

	public String toString() {
		return Integer.toString(this.value);
	}
}
