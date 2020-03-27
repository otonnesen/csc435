package ir;

import codegen.JasminVisitor;
import type.Type;
import type.AtomicType;

public class ConstantCharacter extends Constant {
	private char value;

	public ConstantCharacter(char v) {
		super(new Type(AtomicType.CHARACTER));
		this.value = v;
	}

	public String toString() {
		return "\'" + Character.toString(this.value) + "\'";
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
