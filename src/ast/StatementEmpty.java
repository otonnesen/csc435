package ast;
import visitor.Visitor;

public class StatementEmpty extends Statement {
	public StatementEmpty() {
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
