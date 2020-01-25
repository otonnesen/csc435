package ast;
import visitor.Visitor;

public class StatementPrint extends Statement {
	private Expression e;

	public StatementPrint(Expression e) {
		this.e = e;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
