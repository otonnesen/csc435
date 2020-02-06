package ast;
import visitor.Visitor;

public class StatementArrayAssignment extends Statement {
	private ExpressionArrayAccess eaa;
	private Expression e;

	public StatementArrayAssignment(int line, int offset, ExpressionArrayAccess eaa, Expression e) {
		super(line, offset);
		this.eaa = eaa;
		this.e = e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}

	public ExpressionArrayAccess getArrayAccess() {
		return this.eaa;
	}

	public Expression getExpr() {
		return this.e;
	}
}
