package type;

public class TypeBoolean extends Type {
	private static final String name = "boolean";

	public TypeBoolean() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return t instanceof TypeBoolean;
	}
}
