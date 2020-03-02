package ir;

import type.Type;
import type.AtomicType;

public class ConstantFloat extends Constant {
	private float value;

	public ConstantFloat(float v) {
		super(new Type(AtomicType.FLOAT));
		this.value = v;
	}

	public String toString() {
		return Float.toString(this.value);
	}
}
