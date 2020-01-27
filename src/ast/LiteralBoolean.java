package ast;
import visitor.Visitor;

public class LiteralBoolean extends Literal {
	private boolean value;

	public LiteralBoolean(boolean b) {
		this.value = b;
	}

	public boolean getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
