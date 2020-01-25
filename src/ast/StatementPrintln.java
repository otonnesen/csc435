package ast;
import visitor.Visitor;

public class StatementPrintln extends Statement {
	private Expression e;

	public StatementPrintln(Expression e) {
		this.e = e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
