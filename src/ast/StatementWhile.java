package ast;
import visitor.Visitor;

public class StatementWhile extends Statement {
	private Expression e;
	private Block b;

	public StatementWhile(Expression e, Block b) {
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
