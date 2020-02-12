package type;

public class TypeArray extends Type {
	private Type type;
	private int size;
	private String name;

	public TypeArray(Type type, int size) {
		this.type = type;
		this.size = size;
		this.name = this.type.getName() + "[" + this.size + "]";
	}

	public String getName() {
		return this.name;
	}
}
