package ast;

public class ASTNode {
	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
