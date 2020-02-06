package ast;
import visitor.Visitor;

public class LiteralBoolean extends Literal {
	private boolean value;

	public LiteralBoolean(int line, int offset, boolean b) {
		super(line, offset);
		this.value = b;
	}

	public boolean getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
