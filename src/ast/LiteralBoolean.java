package ast;

public class LiteralBoolean extends Literal {
	private final boolean value;

	public LiteralBoolean(boolean b) {
		this.value = b;
	}

	public String toString() {
		return String.valueOf(this.value);
	}
}
