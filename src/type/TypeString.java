package type;

public class TypeString extends Type {
	private static final String name = "string";

	public TypeString() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return t instanceof TypeString;
	}
}
