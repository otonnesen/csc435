package ast;
import visitor.Visitor;

public class StatementWhile extends Statement {
	private Expression e;
	private Block b;

	public StatementWhile(int line, int offset, Expression e, Block b) {
		super(line, offset);
		this.e = e;
		this.b = b;
	}

	public Expression getExpr() {
		return this.e;
	}

	public Block getBlock() {
		return this.b;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
