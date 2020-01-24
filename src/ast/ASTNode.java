package ast;
import visitor.Visitor;
import visitor.Visitable;

public class ASTNode implements Visitable {
	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
