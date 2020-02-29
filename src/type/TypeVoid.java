package type;

public class TypeVoid extends Type {
	private static final String name = "void";
	private static final TypeVoid INSTANCE = new TypeVoid();

	private TypeVoid(){}

	public static TypeVoid getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}
}
