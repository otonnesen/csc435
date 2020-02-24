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
		SemanticException(String message, ASTNode n) {
			super(String.format("%s at %d:%d.", message, n.getLine(), n.getOffset()));
		}
	}

	private Environment<String, Type> variables;
	private Environment<String, Type> functions;
	private Type returnType;

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
			String message = String.format(
					"Invalid type `%s` for operation `%s`", lhs, op);
			throw new SemanticException(message, e.getLeftExpr());
		}
		if (!lhs.equals(rhs)) {
			// Left- and right-hand sides are not equal.
			String message = String.format(
					"Mismatched types: `%s` and `%s`", lhs, rhs);
			throw new SemanticException(message, e.getRightExpr());
		}
		return lhs;
	}

	private static String getFunctionSignature(FunctionDeclaration fd) {
		String sig = fd.getId();
		sig += "(";
		String sep = "";
		// Append `(type1,type2,...)` and each type's string to
		// the end of the function to allow for overloading.
		for (Declaration p: fd.getParameters()) {
			sig += sep;
			sig += p.getType().toString();
			sep = ", ";
		}
		sig += ")";
		return sig;
	}

	public Type visit(Block b) {
		for (Statement s: b.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Type visit(Declaration d) {
		if (this.variables.inCurrentScope(d.getId().getId())) {
			// Variable already declared
			String message = String.format(
					"Variable `%s` is already declared",
					d.getId().getId());
			throw new SemanticException(message, d.getId());
		}
		if (d.getType().equals(VOID)) {
			// Void declaration
			String message = String.format(
					"Variable `%s` cannot have type `void`",
					d.getId().getId());
			throw new SemanticException(message, d.getId());
		}
		this.variables.put(d.getId().getId(), d.getType());
		return d.getType();
	}
	public Type visit(ExpressionArrayAccess e) {
		Type id = e.getId().accept(this);
		Type expr = e.getExpr().accept(this);
		if (!expr.equals(INTEGER)) {
			// Array index is not an integer
			String message = String.format(
					"Invalid type `%s` for array access, expected type `int`",
					expr);
			throw new SemanticException(message, e.getExpr());
		}
		if (!(this.variables.lookup(e.getId().getId()) instanceof TypeArray)) {
			String message = String.format(
					"Cannot index into non-array variable `%s`",
					e.getId().getId());
			throw new SemanticException(message, e.getId());
		}
		return id;
	}
	public Type visit(ExpressionFunctionCall e) {
		String id = e.getId();
		id += "(";
		String sep = "";
		for (Expression p: e.getExprList()) {
			id += sep;
			id += p.accept(this).toString();
			sep = ", ";
		}
		id += ")";
		if (!this.functions.inCurrentScope(id)) {
			String message = String.format(
					"Function `%s` not declared", id);
			throw new SemanticException(message, e);
		}
		return this.functions.lookup(id);
	}
	public Type visit(ExpressionIdentifier e) {
		Type t = this.variables.lookup(e.getId());
		if (t == null) {
			// Variable is not declared
			String message = String.format(
					"Variable `%s` is not declared.", e.getId());
			throw new SemanticException(message, e);
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
		return e.getExpr().accept(this);
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
		String sig = getFunctionSignature(fd);
		for (Declaration p: fd.getParameters()) {
			p.accept(this);
		}
		return fd.getType();
	}
	public Type visit(Function f) {
		this.variables.beginScope();
		Type t = f.getDeclaration().accept(this);
		this.returnType = t;
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
		this.functions.beginScope();
		for (Function f: p.getFunctions()) {
			// Add function declarations to function table
			String sig = getFunctionSignature(f.getDeclaration());
			if (this.functions.inCurrentScope(sig)) {
				String message = String.format(
						"Function `%s` is already declared",
						sig);
				throw new SemanticException(message, f.getDeclaration());
			}
			this.functions.put(sig, f.getDeclaration().getType());
		}
		if (this.functions.lookup("main()") == null) {
			throw new SemanticException("main() function not declared", p);
		}
		if (!this.functions.lookup("main()").equals(VOID)) {
			throw new SemanticException(
					"main() function must have return type `void`", p);
		}
		for (Function f: p.getFunctions()) {
			this.variables.beginScope();
			f.accept(this);
			this.variables.endScope();
		}
		this.functions.endScope();
		return null;
	}
	public Type visit(StatementArrayAssignment s) {
		Type idType = s.getArrayAccess().accept(this);
		Type exprType = s.getExpr().accept(this);
		if (!idType.equals(exprType)) {
			// Mismatched types
			String message = String.format(
					"Cannot assign value of type `%s` to array `%s` of type `%s`",
					exprType, s.getArrayAccess().getId().getId(), idType);
			throw new SemanticException(message, s.getArrayAccess().getId());
		}
		return idType;
	}
	public Type visit(StatementAssign s) {
		Type idType = s.getId().accept(this);
		Type exprType = s.getExpr().accept(this);
		if (!idType.equals(exprType)) {
			// Mismatched types
			String message = String.format(
					"Cannot assign value of type `%s` to variable `%s` of type `%s`",
					exprType, s.getId().getId(), idType);
			throw new SemanticException(message, s.getId());
		}
		return idType;
	}
	public Type visit(StatementEmpty s) {
		return null;
	}
	public Type visit(StatementExpression s) {
		return s.getExpr().accept(this);
	}
	public Type visit(StatementIf s) {
		Type t = s.getExpr().accept(this);
		if (!t.equals(BOOLEAN)) {
			// While condition is not boolean
			String message = String.format(
					"`%` found in if condition, expected boolean",
					t);
			throw new SemanticException(message, s.getExpr());
		}
		s.getIfBlock().accept(this);
		if (s.getElseBlock() != null) {
			s.getElseBlock().accept(this);
		}
		return null;
	}
	public Type visit(StatementPrint s) {
		Type t = s.getExpr().accept(this);
		if (!OperationTypes.opTypes.get("print").contains(t)) {
			// Type not printable
			String message = String.format(
					"Cannot print type `%s`", t);
			throw new SemanticException(message, s.getExpr());
		}
		return null;
	}
	public Type visit(StatementPrintln s) {
		Type t = s.getExpr().accept(this);
		if (!OperationTypes.opTypes.get("print").contains(t)) {
			// Type not printable
			String message = String.format(
					"Cannot print type `%s`", t);
			throw new SemanticException(message, s.getExpr());
		}
		return null;
	}
	public Type visit(StatementReturn s) {
		Type t = s.getExpr().accept(this);
		if (!t.equals(this.returnType)) {
			// Expression does not match return type
			String message = String.format(
					"Return value of type `%s` does not match return type `%s`",
					t, this.returnType);
			throw new SemanticException(message, s.getExpr());
		}
		return t;
	}
	public Type visit(StatementWhile s) {
		Type t = s.getExpr().accept(this);
		if (!t.equals(BOOLEAN)) {
			// While condition is not boolean
			String message = String.format(
					"`%s` found in while condition, expected boolean",
					t);
			throw new SemanticException(message, s.getExpr());
		}
		s.getBlock().accept(this);
		return null;
	}
	public Type visit(VariableDeclaration v) {
		return v.getDeclaration().accept(this);
	}
}
