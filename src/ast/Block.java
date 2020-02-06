package ast;
import java.util.ArrayList;
import visitor.Visitor;

public class Block extends ASTNode {
	private ArrayList<Statement> statements;

	public Block() {
		super(-1, -1);
		this.statements = new ArrayList<Statement>();
	}

	public void addStatement(Statement s) {
		this.statements.add(s);
	}

	public ArrayList<Statement> getStatements() {
		return this.statements;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
