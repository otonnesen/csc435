package type;

public class TypeFloat extends Type {
	private static final String name = "float";
	private static final TypeFloat INSTANCE = new TypeFloat();

	private TypeFloat(){}

	public static TypeFloat getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}

	public String IRString() {
		return "F";
	}
}
