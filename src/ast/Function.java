package ast;

import visitor.Visitor;

public class Function extends ASTNode {
	private FunctionDeclaration decl;
	private FunctionBody body;

	public Function(FunctionDeclaration decl, FunctionBody body) {
		this.decl = decl;
		this.body = body;
	}

	public FunctionDeclaration getDeclaration() {
		return this.decl;
	}

	public FunctionBody getBody() {
		return this.body;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
