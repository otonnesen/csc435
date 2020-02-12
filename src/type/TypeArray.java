package type;

public class TypeArray extends Type {
	private Type type;
	private int size;
	private String name;

	public TypeArray(Type type, int size) {
		this.type = type;
		this.size = size;
		this.name = this.type.toString() + "[" + this.size + "]";
	}

	public String toString() {
		return this.name;
	}
}
