package ast;

import visitor.Visitor;
import type.Type;

public class VariableDeclaration extends ASTNode {
	private Declaration d;

	public VariableDeclaration(Declaration d) {
		this.d = d;
	}

	public Declaration getDeclaration() {
		return this.d;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
