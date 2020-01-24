package ast;

public class StatementPrintln extends Statement {
	private Expression e;

	public StatementPrintln(Expression e) {
		this.e = e;
	}
}
