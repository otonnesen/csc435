package ir;

public class ConstantBoolean extends Constant {
	private boolean value;

	public ConstantBoolean(boolean v) {
		super(new Type(AtomicType.BOOLEAN));
		this.value = v;
	}

	public String toString() {
		return "ConstantBoolean: TODO";
	}
}
