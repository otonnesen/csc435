package ir;

import type.Type;

public class Temp extends Operand {
	public enum tempClass {
		PARAM,
		LOCAL,
		TEMP
	}

	private int number;
	private tempClass cls;

	public Temp(int number, Type type, tempClass cls) {
		super(type);
		this.number = number;
		this.cls = cls;
	}

	public int getNumber() {
		return this.number;
	}

	public tempClass getCls() {
		return this.cls;
	}

	public String toString() {
		return "T" + this.number;
	}
}
