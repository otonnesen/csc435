package ir;

import type.Type;

public class TempFactory {
	private final int MAX_LOCALS = 65536;
	private int next;

	public Temp getTemp(Type t) {
		return new Temp(this.next++, Temp.Class.UNKNOWN, t);
	}
}
