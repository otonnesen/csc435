package ast;
import visitor.Visitor;

public class LiteralInteger extends Literal {
	private int value;

	public LiteralInteger(int i) {
		this.value = i;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
