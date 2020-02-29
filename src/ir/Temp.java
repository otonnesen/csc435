package ir;

import type.Type;

public class Temp {
	public enum tempClass {
		PARAM,
		LOCAL,
		TEMP
	}

	private int number;
	private tempClass cls;
	private Type type;

	public Temp(int number, tempClass cls, Type type) {
		this.number = number;
		this.cls = cls;
		this.type = type;
	}

	public String toString() {
		return "T" + this.number;
	}

	public int getNumber() {
		return this.number;
	}

	public tempClass getClass() {
		return this.cls;
	}

	public Type getType() {
		return this.type;
	}
}
