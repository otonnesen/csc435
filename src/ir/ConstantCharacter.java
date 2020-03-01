package ir;

public class ConstantCharacter extends Constant {
	private char value;

	public ConstantCharacter(char v) {
		super(new Type(AtomicType.CHARACTER));
		this.value = v;
	}

	public String toString() {
		return "ConstantCharacter: TODO";
	}
}
