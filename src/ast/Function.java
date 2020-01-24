package ast;

public class Function extends ASTNode {
	private FunctionDeclaration decl;
	private FunctionBody body;

	public Function(FunctionDeclaration decl, FunctionBody body) {
		this.decl = decl;
		this.body = body;
	}
}
