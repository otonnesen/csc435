package ir;

import codegen.JasminVisitor;
import type.Type;
import type.AtomicType;

public class ConstantString extends Constant {
	private String value;

	public ConstantString(String v) {
		super(new Type(AtomicType.STRING));
		this.value = v;
	}

	public String getValue() {
		return this.toString();
	}

	public String toString() {
		return "\"" + this.value + "\"";
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
