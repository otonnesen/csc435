package ir;

public class ArrayType extends Type {
	public ArrayType(AtomicType type) {
		super(type);
	}
	
	public String toString() {
		return "A" + this.type.toString();
	}
}
