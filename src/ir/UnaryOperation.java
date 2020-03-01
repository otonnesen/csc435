package ir;

public class UnaryOperation extends Operand {
	private Temp t;
	private UnaryOperator op;

	public UnaryOperation(AtomicType type, Temp t) {
		super(new Type(type));
		this.t = t;
	}

	public String toString() {
		return this.type.toString() + this.op.toString() + this.t.toString();
	}
}
