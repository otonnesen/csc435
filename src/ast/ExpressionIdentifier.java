package ast;
import visitor.Visitor;

public class ExpressionIdentifier extends Expression {
	private String id;

	public ExpressionIdentifier(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
