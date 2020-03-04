package ir;

import type.Type;

public class TempFactory {
	private final int MAX_LOCALS = 65536;
	private int next;

	public TempFactory() {
		this.next = 0;
	}

	public Temp get(Type t, Temp.tempClass cls) {
		return new Temp(this.next++, t, cls);
	}
}
