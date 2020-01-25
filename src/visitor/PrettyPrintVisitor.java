package visitor;
import ast.*;

public class PrettyPrintVisitor implements Visitor {
	public Object visit(Block b) {
		return null;
	}
	public Object visit(Declaration d) {
		return null;
	}
	public Object visit(ExpressionArrayAccess e) {
		return null;
	}
	public Object visit(ExpressionFunctionCall e) {
		return null;
	}
	public Object visit(ExpressionIdentifier e) {
		System.out.printf("%s", e.getId());
		return null;
	}
	public Object visit(ExpressionIsEqual e) {
		return null;
	}
	public Object visit(ExpressionLessThan e) {
		return null;
	}
	public Object visit(ExpressionList el) {
		return null;
	}
	public Object visit(ExpressionParenthesis e) {
		return null;
	}
	public Object visit(ExpressionPlusMinus e) {
		return null;
	}
	public Object visit(ExpressionTimes e) {
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
		return null;
	}
	public Object visit(LiteralCharacter c) {
		return null;
	}
	public Object visit(LiteralFloat f) {
		return null;
	}
	public Object visit(LiteralInteger i) {
		return null;
	}
	public Object visit(LiteralString s) {
		return null;
	}
	public Object visit(Program p) {
		for (Function f: p.getFunctions()) {
			f.accept(this);
		}
		return null;
	}
	public Object visit(StatementArrayAssignment s) {
		return null;
	}
	public Object visit(StatementAssign s) {
		s.getId().accept(this);
		System.out.printf(" = ", s.getId());
		s.getExpression().accept(this);
		return null;
	}
	public Object visit(StatementEmpty s) {
		return null;
	}
	public Object visit(StatementExpression s) {
		return null;
	}
	public Object visit(StatementIf s) {
		return null;
	}
	public Object visit(StatementPrint s) {
		return null;
	}
	public Object visit(StatementPrintln s) {
		return null;
	}
	public Object visit(StatementReturn s) {
		return null;
	}
	public Object visit(StatementWhile s) {
		return null;
	}
	public Object visit(VariableDeclaration v) {
		System.out.printf("%s ", v.getType().getName());
		v.getId().accept(this);
		System.out.printf(";");
		return null;
	}
}
