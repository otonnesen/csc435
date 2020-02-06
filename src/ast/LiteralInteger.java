package ast;
import visitor.Visitor;

public class LiteralInteger extends Literal {
	private int value;

	public LiteralInteger(int line, int offset, int i) {
		super(line, offset);
		this.value = i;
		System.out.printf("%d, %d\n", this.line, this.offset);
	}

	public int getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
