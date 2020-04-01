package ir;

import codegen.JasminVisitor;
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

	public Temp getTemp() {
		return this.t;
	}

	public UnaryOperator getOp() {
		return this.op;
	}

	public String toString() {
		return this.type.toString() + this.op.toString() + " " + this.t.toString();
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
