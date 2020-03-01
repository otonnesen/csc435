package ir;

import type.TypeInteger;

public class ConstantInteger extends Constant {
	private int value;

	public ConstantInteger(int v) {
		super(TypeInteger.getInstance());
		this.value = v;
	}

	public String toString() {
		return "ConstantInteger: TODO";
	}
}
