package ast;
import visitor.Visitor;
import visitor.Visitable;

public abstract class ASTNode implements Visitable {
	public abstract Object accept(Visitor v);
}
