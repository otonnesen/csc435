package ast;
import type.Type;

public class FunctionDeclaration extends ASTNode {
	private Type type;
	private ExpressionIdentifier id;
	private FormalParameters parameters;

	public FunctionDeclaration(Type type, ExpressionIdentifier id, FormalParameters parameters) {
		this.type = type;
		this.id = id;
		this.parameters = parameters;
	}
}
