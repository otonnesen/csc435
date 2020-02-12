package ast;
import visitor.Visitor;

public class LiteralString extends Literal {
	private String value;

	public LiteralString(String s) {
		this.value = s;
	}

	public String getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
