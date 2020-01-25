package ast;
import visitor.Visitor;

public class StatementEmpty extends Statement {
	public StatementEmpty() {
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
