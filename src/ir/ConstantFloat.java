package ir;

import codegen.JasminVisitor;
import type.Type;
import type.AtomicType;

public class ConstantFloat extends Constant {
	private float value;

	public ConstantFloat(float v) {
		super(new Type(AtomicType.FLOAT));
		this.value = v;
	}

	public float getValue() {
		return this.value;
	}

	public String toString() {
		return Float.toString(this.value);
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
