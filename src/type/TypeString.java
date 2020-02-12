package type;

public class TypeString extends Type {
	private String name;

	public TypeString() {
		this.name = "string";
	}

	public String getName() {
		return this.name;
	}
}
