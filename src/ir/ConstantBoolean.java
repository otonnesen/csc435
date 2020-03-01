package ir;

import type.TypeBoolean;

public class ConstantBoolean extends Constant {
	private boolean value;

	public ConstantBoolean(boolean v) {
		super(TypeBoolean.getInstance());
		this.value = v;
	}

	public String toString() {
		return "ConstantBoolean: TODO";
	}
}
