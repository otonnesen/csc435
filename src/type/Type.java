package type;

public class Type {
	protected AtomicType type;

	public Type(AtomicType type) {
		this.type = type;
	}

	public AtomicType getAtomicType() {
		return this.type;
	}

	public String getName() {
		return this.type.getName();
	}

	public String toString() {
		return this.type.toString();
	}

	public boolean equals(Type other) {
		return this.getAtomicType() == other.getAtomicType();
	}
}
