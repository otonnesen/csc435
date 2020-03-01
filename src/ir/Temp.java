package ir;

import type.Type;

public class Temp {
	public enum tempClass {
		PARAM,
		LOCAL,
		TEMP
	}

	private int number;
	private Type type;
	private tempClass cls;

	public Temp(int number, Type type, tempClass cls) {
		this.number = number;
		this.type = type;
		this.cls = cls;
	}

	public String toString() {
		return "T" + this.number;
	}

	public int getNumber() {
		return this.number;
	}

	public tempClass getCls() {
		return this.cls;
	}

	public Type getType() {
		return this.type;
	}
}
