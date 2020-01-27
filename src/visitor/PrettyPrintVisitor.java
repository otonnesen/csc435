package visitor;
import ast.*;

public class PrettyPrintVisitor extends Visitor {
	private int indentLevel;

	public void printIndent() {
		for (int i = 0; i < indentLevel; i++) System.out.printf("    ");
	}

	public PrettyPrintVisitor() {
		this.indentLevel = 0;
	}

	public Object visit(Block b) {
		System.out.printf("\n");
		this.printIndent();
		System.out.printf("{\n");
		this.indentLevel++;
		for (Statement s: b.getStatements()) {
			this.printIndent();
			s.accept(this);
			System.out.printf("\n");
		}
		this.indentLevel--;
		this.printIndent();
		System.out.printf("}");
		return null;
	}
	public Object visit(Declaration d) {
		System.out.printf("%s %s", d.getType().getName(), d.getId().getId());
		return null;
	}
	public Object visit(ExpressionArrayAccess e) {
		e.getId().accept(this);
		System.out.printf("[");
		e.getExpr().accept(this);
		System.out.printf("]");
		return null;
	}
	public Object visit(ExpressionFunctionCall e) {
		e.getId().accept(this);
		System.out.printf("(");
		String delim = "";
		for (Expression expr: e.getExprList()) {
			System.out.printf("%s", delim);
			delim = ", ";
			expr.accept(this);
		}
		System.out.printf(")");
		return null;
	}
	public Object visit(ExpressionIdentifier e) {
		System.out.printf("%s", e.getId());
		return null;
	}
	public Object visit(ExpressionIsEqual e) {
		e.getLeftExpr().accept(this);
		System.out.printf("==");
		e.getRightExpr().accept(this);
		return null;
	}
	public Object visit(ExpressionLessThan e) {
		e.getLeftExpr().accept(this);
		System.out.printf("<");
		e.getRightExpr().accept(this);
		return null;
	}
	public Object visit(ExpressionMinus e) {
		e.getLeftExpr().accept(this);
		System.out.printf("-");
		e.getRightExpr().accept(this);
		return null;
	}
	public Object visit(ExpressionParenthesis e) {
		System.out.printf("(");
		e.getExpr().accept(this);
		System.out.printf(")");
		return null;
	}
	public Object visit(ExpressionPlus e) {
		e.getLeftExpr().accept(this);
		System.out.printf("+");
		e.getRightExpr().accept(this);
		return null;
	}
	public Object visit(ExpressionTimes e) {
		e.getLeftExpr().accept(this);
		System.out.printf("*");
		e.getRightExpr().accept(this);
		return null;
	}
	public Object visit(FunctionBody fb) {
		System.out.printf("\n");
		this.printIndent();
		System.out.printf("{\n");
		this.indentLevel++;
		String space = "";
		for (VariableDeclaration vd: fb.getVariables()) {
			space = "\n";
			this.printIndent();
			vd.accept(this);
			System.out.printf("\n");
		}
		System.out.printf("%s", space);
		for (Statement s: fb.getStatements()) {
			this.printIndent();
			s.accept(this);
			System.out.printf("\n");
		}
		this.indentLevel--;
		this.printIndent();
		System.out.printf("}\n");
		return null;
	}
	public Object visit(FunctionDeclaration fd) {
		System.out.printf("%s ", fd.getType().getName());
		fd.getId().accept(this);
		System.out.printf("(");
		String delim = "";
		for (Declaration p: fd.getParameters()) {
			System.out.printf("%s", delim);
			delim = ", ";
			p.accept(this);
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
		System.out.printf("%s", b.getValue());
		return null;
	}
	public Object visit(LiteralCharacter c) {
		System.out.printf("'%s'", c.getValue());
		return null;
	}
	public Object visit(LiteralFloat f) {
		System.out.printf("%s", f.getValue());
		return null;
	}
	public Object visit(LiteralInteger i) {
		System.out.printf("%s", i.getValue());
		return null;
	}
	public Object visit(LiteralString s) {
		System.out.printf("\"%s\"", s.getValue());
		return null;
	}
	public Object visit(Program p) {
		String delim = "";
		for (Function f: p.getFunctions()) {
			System.out.printf("%s", delim);
			delim = "\n";
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
		System.out.printf("if (");
		s.getExpr().accept(this);
		System.out.printf(")");
		s.getIfBlock().accept(this);
		if (s.getElseBlock() != null) {
			System.out.printf("\n");
			this.printIndent();
			System.out.printf("else ");
			s.getElseBlock().accept(this);
		}
		return null;
	}
	public Object visit(StatementPrint s) {
		System.out.printf("print ");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementPrintln s) {
		System.out.printf("println ");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementReturn s) {
		System.out.printf("return");
		if (s.getExpr() != null) {
			System.out.printf(" ");
			s.getExpr().accept(this);
		}
		System.out.printf(";");
		return null;
	}
	public Object visit(StatementWhile s) {
		System.out.printf("while (");
		s.getExpr().accept(this);
		System.out.printf(") ");
		s.getBlock().accept(this);
		return null;
	}
	public Object visit(VariableDeclaration v) {
		System.out.printf("%s ", v.getType().getName());
		v.getId().accept(this);
		System.out.printf(";");
		return null;
	}
}
