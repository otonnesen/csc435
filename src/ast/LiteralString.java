package ast;
import visitor.Visitor;

public class LiteralString extends Literal {
	private String value;

	public LiteralString(int line, int offset, String s) {
		super(line, offset);
		this.value = s;
	}

	public String getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
