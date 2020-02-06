package ast;
import visitor.Visitor;

public class ExpressionLessThan extends ExpressionOperation {
	public ExpressionLessThan(int line, int offset, Expression left, Expression right) {
		super(line, offset, left, right);
	}

	public Expression getLeftExpr() {
		return this.left;
	}

	public Expression getRightExpr() {
		return this.right;
	}

	public Object accept(Visitor v) {
		return v.visit(this);
	}
}
