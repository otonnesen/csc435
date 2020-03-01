package ir;

public class Type {
	protected AtomicType type;

	public Type(AtomicType type) {
		this.type = type;
	}

	public AtomicType getAtomicType() {
		return this.type;
	}

	public String toString() {
		return this.type.toString();
	}
}
