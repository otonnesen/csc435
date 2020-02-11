package type;

public class TypeInteger extends Type {
	private static final String name = "int";

	public TypeInteger() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return t instanceof TypeInteger;
	}
}
