package type;

public class TypeCharacter extends Type {
	private static final String name = "char";

	public TypeCharacter() {
	}

	public String toString() {
		return this.name;
	}

	public boolean isComparable(Type t) {
		return t instanceof TypeCharacter;
	}
}
