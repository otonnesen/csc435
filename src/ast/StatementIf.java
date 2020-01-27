package ast;
import visitor.Visitor;

public class StatementIf extends Statement {
	private Expression e;
	private Block ifBlock;
	private Block elseBlock;

	public StatementIf(Expression e, Block ifBlock, Block elseBlock) {
		this.e = e;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}

	public Expression getExpr() {
		return this.e;
	}

	public Block getIfBlock() {
		return this.ifBlock;
	}

	public Block getElseBlock() {
		return this.elseBlock;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
