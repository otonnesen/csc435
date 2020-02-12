package visitor;

import java.util.ArrayList;

import ast.*;
import type.*;

public class TypeCheckVisitor extends Visitor<Type> {

	private class SemanticException extends RuntimeException {
		SemanticException(String message, int line, int offset) {
			super(String.format("Syntax error: %s at %d:%d.",
						message, line, offset));
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
		return null;
	}
	public Type visit(ExpressionLessThan e) {
		return null;
	}
	public Type visit(ExpressionMinus e) {
		return null;
	}
	public Type visit(ExpressionParenthesis e) {
		return null;
	}
	public Type visit(ExpressionPlus e) {
		return null;
	}
	public Type visit(ExpressionTimes e) {
		return null;
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
		return new TypeBoolean();
	}
	public Type visit(LiteralCharacter c) {
		return new TypeCharacter();
	}
	public Type visit(LiteralFloat f) {
		return new TypeFloat();
	}
	public Type visit(LiteralInteger i) {
		return new TypeInteger();
	}
	public Type visit(LiteralString s) {
		return new TypeString();
	}
	public Type visit(Program p) {
		throw new SemanticException("test", 6, 9);
		// TODO: Create function table
		// for (Function f: p.getFunctions()) {
		// 	f.accept(this);
		// }
		// return null;
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
		return null;
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
