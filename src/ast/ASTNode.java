package ast;

import visitor.Visitor;
import visitor.Visitable;

public abstract class ASTNode implements Visitable {
	private int line;
	private int offset;

	public ASTNode() {
		this.line = -1;
		this.offset = -1;
	}

	public int getLine() {
		return this.line;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public abstract <T> T accept(Visitor<T> v);
}
