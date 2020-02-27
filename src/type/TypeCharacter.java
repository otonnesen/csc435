package type;

public class TypeCharacter extends Type {
	private static final String name = "char";
	private static final TypeCharacter INSTANCE = new TypeCharacter();

	private TypeCharacter(){}

	public static TypeCharacter getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}

	public String IRString() {
		return "C";
	}
}
