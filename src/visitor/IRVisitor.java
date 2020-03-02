package visitor;

import ir.*;
import type.Type;

public class IRVisitor extends Visitor<Temp> {
	public Temp visit(ast.Block b) {
		return null;
	}
	public Temp visit(ast.Declaration d) {
		return null;
	}
	public Temp visit(ast.ExpressionArrayAccess e) {
		return null;
	}
	public Temp visit(ast.ExpressionFunctionCall e) {
		return null;
	}
	public Temp visit(ast.ExpressionIdentifier e) {
		return null;
	}
	public Temp visit(ast.ExpressionIsEqual e) {
		return null;
	}
	public Temp visit(ast.ExpressionLessThan e) {
		return null;
	}
	public Temp visit(ast.ExpressionMinus e) {
		return null;
	}
	public Temp visit(ast.ExpressionParenthesis e) {
		return null;
	}
	public Temp visit(ast.ExpressionPlus e) {
		return null;
	}
	public Temp visit(ast.ExpressionTimes e) {
		return null;
	}
	public Temp visit(ast.FunctionBody fb) {
		return null;
	}
	public Temp visit(ast.FunctionDeclaration fd) {
		return null;
	}
	public Temp visit(ast.Function f) {
		return null;
	}
	public Temp visit(ast.LiteralBoolean b) {
		return null;
	}
	public Temp visit(ast.LiteralCharacter c) {
		return null;
	}
	public Temp visit(ast.LiteralFloat f) {
		return null;
	}
	public Temp visit(ast.LiteralInteger i) {
		return null;
	}
	public Temp visit(ast.LiteralString s) {
		return null;
	}
	public Temp visit(ast.Program p) {
		return null;
	}
	public Temp visit(ast.StatementArrayAssignment s) {
		return null;
	}
	public Temp visit(ast.StatementAssign s) {
		return null;
	}
	public Temp visit(ast.StatementEmpty s) {
		return null;
	}
	public Temp visit(ast.StatementExpression s) {
		return null;
	}
	public Temp visit(ast.StatementIf s) {
		return null;
	}
	public Temp visit(ast.StatementPrint s) {
		return null;
	}
	public Temp visit(ast.StatementPrintln s) {
		return null;
	}
	public Temp visit(ast.StatementReturn s) {
		return null;
	}
	public Temp visit(ast.StatementWhile s) {
		return null;
	}
	public Temp visit(ast.VariableDeclaration v) {
		return null;
	}
}
