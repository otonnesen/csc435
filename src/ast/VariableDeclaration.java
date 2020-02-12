package ast;

import visitor.Visitor;
import type.Type;

public class VariableDeclaration extends ASTNode {
	private Type type;
	private ExpressionIdentifier id;

	public VariableDeclaration(Declaration d) {
		this.type = d.getType();
		this.id = d.getId();
	}

	public Type getType() {
		return this.type;
	}

	public ExpressionIdentifier getId() {
		return this.id;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
