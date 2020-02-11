package type;

public class TypeFloat extends Type {
	private static final String name = "float";

	public TypeFloat() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return t instanceof TypeFloat;
	}
}
