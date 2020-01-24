package type;

public class TypeArray extends Type {
	private Type type;
	private int size;

	public TypeArray(Type type, int size) {
		this.type = type;
		this.size = size;
	}
}
