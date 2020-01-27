package visitor;
import ast.*;

public class PrettyPrintVisitor implements Visitor {
	public Object visit(Block b) {
		System.out.printf("<%s: TODO>", b.getClass().getSimpleName());
		return null;
	}
	public Object visit(Declaration d) {
		System.out.printf("<%s: TODO>", d.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionArrayAccess e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionFunctionCall e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionIdentifier e) {
		System.out.printf("%s", e.getId());
		return null;
	}
	public Object visit(ExpressionIsEqual e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionLessThan e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionList el) {
		System.out.printf("<%s: TODO>", el.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionParenthesis e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionPlusMinus e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(ExpressionTimes e) {
		System.out.printf("<%s: TODO>", e.getClass().getSimpleName());
		return null;
	}
	public Object visit(FunctionBody fb) {
		System.out.printf(" {\n");
		for (VariableDeclaration vd: fb.getVariables()) {
			vd.accept(this);
			System.out.printf("\n");
		}
		for (Statement s: fb.getStatements()) {
			s.accept(this);
			System.out.printf("\n");
		}
		System.out.printf("}\n");
		return null;
	}
	public Object visit(FunctionDeclaration fd) {
		System.out.printf("%s ", fd.getType().getName());
		fd.getId().accept(this);
		System.out.printf("( ");
		for (Declaration p: fd.getParameters()) {
			p.accept(this);
			System.out.printf(" ");
		}
		System.out.printf(")");
		return null;
	}
	public Object visit(Function f) {
		f.getDeclaration().accept(this);
		f.getBody().accept(this);
		return null;
	}
	public Object visit(LiteralBoolean b) {
		System.out.printf("<%s: TODO>", b.getClass().getSimpleName());
		return null;
	}
	public Object visit(LiteralCharacter c) {
		System.out.printf("<%s: TODO>", c.getClass().getSimpleName());
		return null;
	}
	public Object visit(LiteralFloat f) {
		System.out.printf("<%s: TODO>", f.getClass().getSimpleName());
		return null;
	}
	public Object visit(LiteralInteger i) {
		System.out.printf("<%s: TODO>", i.getClass().getSimpleName());
		return null;
	}
	public Object visit(LiteralString s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(Program p) {
		for (Function f: p.getFunctions()) {
			f.accept(this);
		}
		return null;
	}
	public Object visit(StatementArrayAssignment s) {
		s.getArrayAccess().accept(this);
		System.out.printf(" = ");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementAssign s) {
		s.getId().accept(this);
		System.out.printf(" = ");
		s.getExpression().accept(this);
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementEmpty s) {
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementExpression s) {
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementIf s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(StatementPrint s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(StatementPrintln s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(StatementReturn s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(StatementWhile s) {
		System.out.printf("<%s: TODO>", s.getClass().getSimpleName());
		return null;
	}
	public Object visit(VariableDeclaration v) {
		System.out.printf("%s ", v.getType().getName());
		v.getId().accept(this);
		System.out.printf(";");
		return null;
	}
}
