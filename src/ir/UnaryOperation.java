package ir;

import type.Type;
import type.AtomicType;

public class UnaryOperation extends Operand {
	private Temp t;
	private UnaryOperator op;

	public UnaryOperation(AtomicType type, Temp t, UnaryOperator op) {
		super(new Type(type));
		this.t = t;
		this.op = op;
	}

	public String toString() {
		return this.type.toString() + this.op.toString() + this.t.toString();
	}
}
