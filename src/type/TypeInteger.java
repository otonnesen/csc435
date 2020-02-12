package type;

// public class TypeInteger extends TypeFloat { // TODO: Implement type conversions
public class TypeInteger extends Type {
	private static final String name = "int";
	private static final TypeInteger INSTANCE = new TypeInteger();

	private TypeInteger(){}

	public static TypeInteger getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}
}
