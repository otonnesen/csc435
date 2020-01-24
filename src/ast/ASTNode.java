package ast;
import visitor.Visitor;

public class ASTNode {
	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
