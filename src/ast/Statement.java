package ast;

public abstract class Statement extends ASTNode {
	public Statement(int line, int offset) {
		super(line, offset);
	}
	public Statement() {
		this(-1, -1);
	}
}
