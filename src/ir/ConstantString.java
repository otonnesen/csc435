package ir;

public class ConstantString extends Constant {
	private String value;

	public ConstantString(String v) {
		super(new Type(AtomicType.STRING));
		this.value = v;
	}

	public String toString() {
		return this.value;
	}
}
