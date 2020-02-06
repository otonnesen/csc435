package ast;
import visitor.Visitor;

public class StatementPrintln extends Statement {
	private Expression e;

	public StatementPrintln(int line, int offset, Expression e) {
		super(line, offset);
		this.e = e;
	}

	public Expression getExpr() {
		return this.e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
