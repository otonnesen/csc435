package ast;

public class StatementReturn extends Statement {
	private Expression e;

	public StatementReturn(Expression e) {
		this.e = e;
	}
}
