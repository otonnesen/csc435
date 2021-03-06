package ast;

import java.util.ArrayList;

import visitor.Visitor;
import type.Type;

public class FunctionDeclaration extends ASTNode {
	private Type type;
	private String id;
	private ArrayList<Declaration> parameters;

	public FunctionDeclaration(Type type, String id, ArrayList<Declaration> parameters) {
		this.type = type;
		this.id = id;
		this.parameters = parameters == null ? new ArrayList<Declaration>() : parameters;
	}

	public Type getType() {
		return this.type;
	}

	public String getId() {
		return this.id;
	}

	public ArrayList<Declaration> getParameters() {
		return this.parameters;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
