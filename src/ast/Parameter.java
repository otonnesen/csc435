package ast;
import type.Type;

public class Parameter extends ASTNode {
	private Type type;
	private ExpressionIdentifier id;

	public Parameter(Type type, ExpressionIdentifier id) {
		this.type = type;
		this.id = id;
	}
}
