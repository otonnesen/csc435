package type;

public class TypeString extends Type {
	private static final String name = "string";
	private static final TypeString INSTANCE = new TypeString();

	private TypeString(){}

	public static TypeString getInstance() {
		return INSTANCE;
	}

	public String toString() {
		return this.name;
	}
}
