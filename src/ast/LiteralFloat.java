package ast;
import visitor.Visitor;

public class LiteralFloat extends Literal {
	private float value;

	public LiteralFloat(float f) {
		this.value = f;
	}

	public String toString() {
		return String.valueOf(this.value);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
