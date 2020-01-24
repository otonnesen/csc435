package ast;

public class ExpressionTimes extends ExpressionOperation {
	public ExpressionTimes(Expression e1, Expression e2) {
		super(e1, e2);
	}

	public String toString() {
		return this.e1 + " * " + this.e2;
	}
}
