package ir;

import type.TypeString;

public class ConstantString extends Constant {
	private String value;

	public ConstantString(String v) {
		super(TypeString.getInstance());
		this.value = v;
	}

	public String toString() {
		return "ConstantString: TODO";
	}
}
