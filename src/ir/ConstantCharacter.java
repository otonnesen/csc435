package ir;

import type.TypeCharacter;

public class ConstantCharacter extends Constant {
	private char value;

	public ConstantCharacter(char v) {
		super(TypeCharacter.getInstance());
		this.value = v;
	}

	public String toString() {
		return "ConstantCharacter: TODO";
	}
}
