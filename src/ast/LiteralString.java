package ast;

public class LiteralString extends Literal {
	private String value;

	public LiteralString(String s) {
		this.value = s;
	}

	public String toString() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
