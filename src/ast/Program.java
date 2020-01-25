package ast;

import java.util.ArrayList;

import visitor.Visitor;

public class Program extends ASTNode {
	private ArrayList<Function> functions;

	public Program() {
		this.functions = new ArrayList<Function>();
	}

	public void addFunction(Function f) {
		this.functions.add(f);
	}

	public ArrayList<Function> getFunctions() {
		return this.functions;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
