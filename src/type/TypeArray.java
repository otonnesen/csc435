package type;

public class TypeArray extends Type {
	private int size;

	public TypeArray(AtomicType type, int size) {
		super(type);
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public String getName() {
		return this.type.getName() + "[" + this.size + "]";
	}

	public String toString() {
		return "A" + this.type.toString();
	}

	public boolean equals(TypeArray other) {
		if (!(other instanceof TypeArray))
			return false;
		return this.getAtomicType().equals(other.getAtomicType()) &&
			this.getSize() == other.getSize();
	}
}
