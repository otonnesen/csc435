package visitor;

import java.util.ArrayList;

import ast.*;
import type.*;

public class TypeCheckVisitor extends Visitor<Type> {

	private class SemanticException extends RuntimeException {
		SemanticException(String message, int line, int offset) {
			super(String.format("%s at %d:%d.", message, line, offset));
		}
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
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		if (!OperationTypes.equals.contains(lhs)) {
			// This operation cannot be applied to the left-hand expression
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Invalid type `%s` for operation `==`.", lhs);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Types do not match: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
		return lhs;
	}
	public Type visit(ExpressionLessThan e) {
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		if (!OperationTypes.lessThan.contains(lhs)) {
			// This operation cannot be applied to the left-hand expression
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Invalid type `%s` for operation `<`.", lhs);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Types do not match: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
		return lhs;
	}
	public Type visit(ExpressionMinus e) {
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		if (!OperationTypes.minus.contains(lhs)) {
			// This operation cannot be applied to the left-hand expression
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Invalid type `%s` for operation `-`.", lhs);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Types do not match: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
		return lhs;
	}
	public Type visit(ExpressionParenthesis e) {
		return null;
	}
	public Type visit(ExpressionPlus e) {
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		if (!OperationTypes.plus.contains(lhs)) {
			// This operation cannot be applied to the left-hand expression
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Invalid type `%s` for operation `+`.", lhs);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Types do not match: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
		return lhs;
	}
	public Type visit(ExpressionTimes e) {
		Type lhs = e.getLeftExpr().accept(this);
		Type rhs = e.getRightExpr().accept(this);
		if (!OperationTypes.times.contains(lhs)) {
			// This operation cannot be applied to the left-hand expression
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Invalid type `%s` for operation `*`.", lhs);
			throw new SemanticException(message, line, offset);
		}
		if (!lhs.comparable(rhs)) {
			// Left- and right-hand sides are not comparable.
			int line = e.getRightExpr().getLine();
			int offset = e.getRightExpr().getOffset();
			String message = String.format("Types do not match: `%s` and `%s`.", lhs, rhs);
			throw new SemanticException(message, line, offset);
		}
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
		return TypeBoolean.getInstance();
	}
	public Type visit(LiteralCharacter c) {
		return TypeCharacter.getInstance();
	}
	public Type visit(LiteralFloat f) {
		return TypeFloat.getInstance();
	}
	public Type visit(LiteralInteger i) {
		return TypeInteger.getInstance();
	}
	public Type visit(LiteralString s) {
		return TypeString.getInstance();
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
