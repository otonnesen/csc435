package ast;
import visitor.Visitor;

public class LiteralCharacter extends Literal {
	private char value;

	public LiteralCharacter(int line, int offset, char c) {
		super(line, offset);
		this.value = c;
	}

	public char getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
