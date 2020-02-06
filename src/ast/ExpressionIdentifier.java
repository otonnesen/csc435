package ast;
import visitor.Visitor;

public class ExpressionIdentifier extends Expression {
	private String id;

	public ExpressionIdentifier(int line, int offset, String id) {
		super(line, offset);
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
