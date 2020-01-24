package ast;

public class StatementIf extends Statement {
	private Expression e;
	private Block ifBlock;
	private Block elseBlock;

	public StatementIf(Expression e, Block ifBlock, Block elseBlock) {
		this.e = e;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
	}
}
