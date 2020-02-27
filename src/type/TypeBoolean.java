package type;

public class TypeBoolean extends Type {
	private static final String name = "boolean";
	private static final TypeBoolean INSTANCE = new TypeBoolean();

	private TypeBoolean(){}

	public static TypeBoolean getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}

	public String IRString() {
		return "Z";
	}
}
