package ast;

public class StatementArrayAssignment extends Statement {
	private ExpressionArrayAccess eaa;
	private Expression e;

	public StatementArrayAssignment(ExpressionArrayAccess eaa, Expression e) {
		this.eaa = eaa;
		this.e = e;
	}
}
