package ast;

public class LiteralInteger extends Literal {
	private final int value;

	public LiteralInteger(int i) {
		this.value = i;
	}

	public String toString() {
		return String.valueOf(this.value);
	}
}
