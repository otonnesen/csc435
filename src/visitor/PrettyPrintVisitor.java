package visitor;

import ast.*;

public class PrettyPrintVisitor extends Visitor<Void> {
	private int indentLevel;

	private void printIndent() {
		for (int i = 0; i < indentLevel; i++) System.out.printf("    ");
	}

	public PrettyPrintVisitor() {
		this.indentLevel = 0;
	}

	public Void visit(Block b) {
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
	public Void visit(Declaration d) {
		System.out.printf("%s %s", d.getType().toString(), d.getId().getId());
		return null;
	}
	public Void visit(ExpressionArrayAccess e) {
		e.getId().accept(this);
		System.out.printf("[");
		e.getExpr().accept(this);
		System.out.printf("]");
		return null;
	}
	public Void visit(ExpressionFunctionCall e) {
		System.out.printf("%s", e.getId());
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
	public Void visit(ExpressionIdentifier e) {
		System.out.printf("%s", e.getId());
		return null;
	}
	public Void visit(ExpressionIsEqual e) {
		e.getLeftExpr().accept(this);
		System.out.printf("%s", e);
		e.getRightExpr().accept(this);
		return null;
	}
	public Void visit(ExpressionLessThan e) {
		e.getLeftExpr().accept(this);
		System.out.printf("%s", e);
		e.getRightExpr().accept(this);
		return null;
	}
	public Void visit(ExpressionMinus e) {
		e.getLeftExpr().accept(this);
		System.out.printf("%s", e);
		e.getRightExpr().accept(this);
		return null;
	}
	public Void visit(ExpressionParenthesis e) {
		System.out.printf("(");
		e.getExpr().accept(this);
		System.out.printf(")");
		return null;
	}
	public Void visit(ExpressionPlus e) {
		e.getLeftExpr().accept(this);
		System.out.printf("%s", e);
		e.getRightExpr().accept(this);
		return null;
	}
	public Void visit(ExpressionTimes e) {
		e.getLeftExpr().accept(this);
		System.out.printf("%s", e);
		e.getRightExpr().accept(this);
		return null;
	}
	public Void visit(FunctionBody fb) {
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
	public Void visit(FunctionDeclaration fd) {
		System.out.printf("%s %s", fd.getType().toString(), fd.getId());
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
	public Void visit(Function f) {
		f.getDeclaration().accept(this);
		f.getBody().accept(this);
		return null;
	}
	public Void visit(LiteralBoolean b) {
		System.out.printf("%s", b.getValue());
		return null;
	}
	public Void visit(LiteralCharacter c) {
		System.out.printf("'%s'", c.getValue());
		return null;
	}
	public Void visit(LiteralFloat f) {
		System.out.printf("%s", f.getValue());
		return null;
	}
	public Void visit(LiteralInteger i) {
		System.out.printf("%s", i.getValue());
		return null;
	}
	public Void visit(LiteralString s) {
		System.out.printf("\"%s\"", s.getValue());
		return null;
	}
	public Void visit(Program p) {
		String delim = "";
		for (Function f: p.getFunctions()) {
			System.out.printf("%s", delim);
			delim = "\n";
			f.accept(this);
		}
		return null;
	}
	public Void visit(StatementArrayAssignment s) {
		s.getArrayAccess().accept(this);
		System.out.printf("=");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementAssign s) {
		s.getId().accept(this);
		System.out.printf("=");
		s.getExpression().accept(this);
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementEmpty s) {
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementExpression s) {
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementIf s) {
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
	public Void visit(StatementPrint s) {
		System.out.printf("print ");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementPrintln s) {
		System.out.printf("println ");
		s.getExpr().accept(this);
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementReturn s) {
		System.out.printf("return");
		if (s.getExpr() != null) {
			System.out.printf(" ");
			s.getExpr().accept(this);
		}
		System.out.printf(";");
		return null;
	}
	public Void visit(StatementWhile s) {
		System.out.printf("while (");
		s.getExpr().accept(this);
		System.out.printf(") ");
		s.getBlock().accept(this);
		return null;
	}
	public Void visit(VariableDeclaration v) {
		v.getDeclaration().accept(this);
		System.out.printf(";");
		return null;
	}
}
