package ast;
import visitor.Visitor;

public class StatementWhile extends Statement {
	public StatementWhile() {
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
