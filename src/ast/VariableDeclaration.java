package ast;
import type.Type;

public class VariableDeclaration extends ASTNode {
	private Type type;
	private ExpressionIdentifier id;
	public VariableDeclaration(Type type, ExpressionIdentifier id) {
		this.type = type;
		this.id = id;
	}
}
