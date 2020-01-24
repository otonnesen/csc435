package ast;

public class StatementPrint extends Statement {
	private Expression e;

	public StatementPrint(Expression e) {
		this.e = e;
	}
}
