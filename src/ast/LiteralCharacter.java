package ast;
import visitor.Visitor;

public class LiteralCharacter extends Literal {
	private char value;

	public LiteralCharacter(char c) {
		this.value = c;
	}

	public String toString() {
		return String.valueOf(this.value);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
