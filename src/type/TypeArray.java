package type;

public class TypeArray extends Type {
	private Type type;
	private int size;
	private String name;

	public TypeArray(Type type, int size) {
		this.type = type;
		this.size = size;
		this.name = this.type.toString() + "[" + this.size + "]";
	}

	public int getSize() {
		return this.size;
	}

	public Type getType() {
		return this.type;
	}

	public String toString() {
		return this.name;
	}

	public boolean equals(TypeArray other) {
		if (!(other instanceof TypeArray))
			return false;
		return this.getType().equals(other.getType()) &&
			this.getSize() == other.getSize();
	}

	public boolean comparable(TypeArray other) {
		if (!(other instanceof TypeArray))
			return false;
		return this.getType().comparable(other.getType()) &&
			this.getSize() == other.getSize();
	}
}
