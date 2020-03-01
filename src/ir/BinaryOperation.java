package ir;

public class BinaryOperation extends Operand {
	private Temp t1;
	private Temp t2;
	private BinaryOperator op;

	public BinaryOperation(AtomicType type, Temp t1, Temp t2, BinaryOperator op) {
		super(new Type(type));
		this.t1 = t1;
		this.t2 = t2;
		this.op = op;
	}

	public String toString() {
		return this.t1.toString() + this.type.toString() +
			this.op.toString() + this.t2.toString();
	}
}
