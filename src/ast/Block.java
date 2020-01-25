package ast;
import java.util.ArrayList;
import visitor.Visitor;

public class Block extends ASTNode {
	private ArrayList<Statement> statements;

	public Block() {
		this.statements = new ArrayList<Statement>();
	}

	public void addStatement(Statement s) {
		this.statements.add(s);
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
