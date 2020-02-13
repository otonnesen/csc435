package visitor;

import java.util.ArrayList;

import ast.*;
import type.*;
import environment.Environment;

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

	private Environment<String, Type> variables;
	private Environment<String, Type> functions;

	public TypeCheckVisitor() {
		this.variables = new Environment<String, Type>();
		this.functions = new Environment<String, Type>();
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
		if (this.variables.inCurrentScope(d.getId().getId())) {
			int line = d.getId().getLine();
			int offset = d.getId().getOffset();
			String message = String.format("Variable `%s` is already declared.",
					d.getId().getId());
			throw new SemanticException(message, line, offset);
		}
		this.variables.put(d.getId().getId(), d.getType());
		return d.getType();
	}
	public Type visit(ExpressionArrayAccess e) {
		Type id = e.getId().accept(this);
		Type expr = e.getExpr().accept(this);
		if (!expr.equals(INTEGER)) {
			// Array index is not an integer
			int line = e.getExpr().getLine();
			int offset = e.getExpr().getOffset();
			String message = String.format(
					"Invalid type `%s` for array access, expected type `int`.",
					expr);
			throw new SemanticException(message, line, offset);
		}
		return id;
	}
	public Type visit(ExpressionFunctionCall e) {
		return null;
	}
	public Type visit(ExpressionIdentifier e) {
		Type t = this.variables.lookup(e.getId());
		if (t == null) {
			// Variable is not declared
			int line = e.getLine();
			int offset = e.getOffset();
			String message = String.format(
					"Variable `%s` is not declared.", e.getId());
			throw new SemanticException(message, line, offset);
		}
		return t;
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
		for (VariableDeclaration vd: fb.getVariables()) {
			vd.accept(this);
		}

		for (Statement s: fb.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Type visit(FunctionDeclaration fd) {
		String id = fd.getId();
		// Append `|` and each type's string to the end of the function
		// to allow for overloading.
		id += "|";
		for (Declaration p: fd.getParameters()) {
			p.accept(this);
			id += p.getType().toString();
			// TODO: Add parameters to variable table
		}
		if (this.functions.inCurrentScope(id)) {
			int line = fd.getLine();
			int offset = fd.getOffset();
			String message = String.format("Function `%s` is already declared.",
					fd.getId());
			throw new SemanticException(message, line, offset);
		}
		this.functions.put(id, fd.getType());
		return fd.getType();
	}
	public Type visit(Function f) {
		this.variables.beginScope();
		Type t = f.getDeclaration().accept(this);
		f.getBody().accept(this);
		this.variables.endScope();
		return t;
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
		this.variables.beginScope();
		for (Function f: p.getFunctions()) {
			this.functions.beginScope();
			f.accept(this);
			this.variables.endScope();
		}
		this.functions.endScope();
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
		return v.getDeclaration().accept(this);
	}
}
