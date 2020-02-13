package visitor;

import java.util.ArrayList;

import ast.*;
import type.*;

public class TypeCheckVisitor extends Visitor<Type> {

	private static final Type BOOLEAN = TypeBoolean.getInstance();
	private static final Type CHARACTER = TypeCharacter.getInstance();
	private static final Type FLOAT = TypeFloat.getInstance();
	private static final Type INTEGER = TypeInteger.getInstance();
	private static final Type STRING = TypeString.getInstance();
	private static final Type VOID = TypeVoid.getInstance();

	private class SemanticException extends RuntimeException {
		SemanticException(String message, int line, int offset) {
			super(String.format("%s at %d:%d.", message, line, offset));
		}
	}

	private Type checkExpression(ExpressionOperation e) {
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		String op = e.toString();
		if (!OperationTypes.opTypes.get(op).contains(lhs)) {
			// Operation is not applicable to left-hand expression
			int line = e.getLeftExpr().getLine();
			int offset = e.getLeftExpr().getOffset();
			String message = String.format(
					"Invalid type `%s` for operation `%s`.", lhs, op);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format(
					"Mismatched types: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
		return lhs;
	}

	public Type visit(Block b) {
		for (Statement s: b.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Type visit(Declaration d) {
		Type t = d.getType();
		d.getId().accept(this);
		// TODO: Add to variable table
		return t;
	}
	public Type visit(ExpressionArrayAccess e) {
		return null;
	}
	public Type visit(ExpressionFunctionCall e) {
		return null;
	}
	public Type visit(ExpressionIdentifier e) {
		return null;
	}
	public Type visit(ExpressionIsEqual e) {
		Type lhs = checkExpression(e);
		return BOOLEAN;
	}
	public Type visit(ExpressionLessThan e) {
		Type lhs = checkExpression(e);
		return BOOLEAN;
	}
	public Type visit(ExpressionMinus e) {
		Type lhs = checkExpression(e);
		return lhs;
	}
	public Type visit(ExpressionParenthesis e) {
		return null;
	}
	public Type visit(ExpressionPlus e) {
		Type lhs = checkExpression(e);
		return lhs;
	}
	public Type visit(ExpressionTimes e) {
		Type lhs = checkExpression(e);
		return lhs;
	}
	public Type visit(FunctionBody fb) {
		// for (Declaration d: fb.getVariables()) {
		// 	d.accept(this);
		// }

		for (Statement s: fb.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Type visit(FunctionDeclaration fd) {
		fd.getId().accept(this);
		ArrayList<Type> params = new ArrayList<Type>();
		for (Declaration p: fd.getParameters()) {
			// params.add(p.accept(this));
			// TODO: Add parameters to variable table
		}
		// TODO: Add function to function table
		return fd.getType();
	}
	public Type visit(Function f) {
		Object t = f.getDeclaration().accept(this);
		f.getBody().accept(this);
		return null;
		// return t;
	}
	public Type visit(LiteralBoolean b) {
		return BOOLEAN;
	}
	public Type visit(LiteralCharacter c) {
		return CHARACTER;
	}
	public Type visit(LiteralFloat f) {
		return FLOAT;
	}
	public Type visit(LiteralInteger i) {
		return INTEGER;
	}
	public Type visit(LiteralString s) {
		return STRING;
	}
	public Type visit(Program p) {
		// TODO: Create function table
		for (Function f: p.getFunctions()) {
			f.accept(this);
		}
		return null;
	}
	public Type visit(StatementArrayAssignment s) {
		return null;
	}
	public Type visit(StatementAssign s) {
		return null;
	}
	public Type visit(StatementEmpty s) {
		return null;
	}
	public Type visit(StatementExpression s) {
		return s.getExpr().accept(this);
	}
	public Type visit(StatementIf s) {
		return null;
	}
	public Type visit(StatementPrint s) {
		return null;
	}
	public Type visit(StatementPrintln s) {
		return null;
	}
	public Type visit(StatementReturn s) {
		return null;
	}
	public Type visit(StatementWhile s) {
		return null;
	}
	public Type visit(VariableDeclaration v) {
		return null;
	}
}
