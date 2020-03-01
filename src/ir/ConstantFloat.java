package ir;

import type.TypeFloat;

public class ConstantFloat extends Constant {
	private float value;

	public ConstantFloat(float v) {
		super(TypeFloat.getInstance());
		this.value = v;
	}

	public String toString() {
		return "ConstantFloat: TODO";
	}
}
