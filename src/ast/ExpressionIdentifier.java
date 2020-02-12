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

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
