package type;

public class TypeVoid extends Type {
	private static final String name = "void";

	public TypeVoid() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return false;
	}
}
