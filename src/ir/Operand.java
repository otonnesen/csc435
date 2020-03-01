package ir;

public abstract class Operand {
	private Type type;

	public Operand(Type t) {
		this.type = t;
	}

	public Type getType() {
		return this.type;
	}
}
