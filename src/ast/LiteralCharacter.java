package ast;

public class LiteralCharacter extends Literal {
	private final char value;

	public LiteralCharacter(char c) {
		this.value = c;
	}

	public String toString() {
		return String.valueOf(this.value);
	}
}