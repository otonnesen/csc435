package ast;
import visitor.Visitor;

public class LiteralFloat extends Literal {
	private float value;

	public LiteralFloat(int line, int offset, float f) {
		super(line, offset);
		this.value = f;
	}

	public float getValue() {
		return this.value;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
