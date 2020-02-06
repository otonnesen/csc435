package ast;

public abstract class Expression extends ASTNode {
	public Expression(int line, int offset) {
		super(line, offset);
	}

	public Expression() {
		super(-1, -1);
	}
}
