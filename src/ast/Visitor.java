package ast;

public abstract class Visitor {
	public abstract Object visit(ASTNode n);
	public abstract Object visit(LiteralBoolean b);
	public abstract Object visit(LiteralCharacter c);
	public abstract Object visit(LiteralFloat f);
	public abstract Object visit(LiteralInteger i);
	public abstract Object visit(LiteralString s);
}
