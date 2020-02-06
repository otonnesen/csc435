package ast;
import visitor.Visitor;
import visitor.Visitable;

public abstract class ASTNode implements Visitable {
	protected int line;
	protected int offset;

	public ASTNode(int line, int offset) {
		this.line = line;
		this.offset = offset;
	}

	public abstract Object accept(Visitor v);
}
