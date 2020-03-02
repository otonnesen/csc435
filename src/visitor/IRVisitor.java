package visitor;

import ir.*;
import type.Type;
import environment.Environment;

import java.util.ArrayList;

public class IRVisitor extends Visitor<Temp> {
	private Environment<String, Type> functions;
	private Environment<String, Temp> variables;
	private Program program;
	private TempFactory tf;
	private Function curFunc;
	private String filename;

	private static Temp.tempClass PARAM = Temp.tempClass.PARAM;
	private static Temp.tempClass LOCAL = Temp.tempClass.LOCAL;
	private static Temp.tempClass TEMP = Temp.tempClass.TEMP;

	private static MethodType getSig(ast.Function f) {
		ArrayList<Type> params = new ArrayList<Type>();
		for (ast.Declaration d: f.getDeclaration().getParameters()) {
			params.add(d.getType());
		}
		return new MethodType(f.getDeclaration().getType(), params);
	}

	public IRVisitor(String filename) {
		this.functions = new Environment<String, Type>();
		this.variables = new Environment<String, Temp>();
		this.filename = filename;
	}

	public Temp visit(ast.Block b) {
		return null;
	}
	public Temp visit(ast.Declaration d) {
		Temp t = tf.getTemp(d.getType(), LOCAL);
		this.variables.put(d.getId().getId(), t);
		return t;
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
		for (ast.VariableDeclaration vd: fb.getVariables()) {
			this.curFunc.addTemp(vd.accept(this));
		}

		for (ast.Statement s: fb.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Temp visit(ast.FunctionDeclaration fd) {
		for (ast.Declaration p: fd.getParameters()) {
			Temp t = tf.getTemp(p.getType(), PARAM);
			this.variables.put(p.getId().getId(), t);
			this.curFunc.addTemp(t);
		}
		return null;
	}
	public Temp visit(ast.Function f) {
		this.variables.beginScope();

		MethodType type = this.getSig(f);
		Function fn = new Function(f.getDeclaration().getId(), type);
		this.curFunc = fn;

		f.getDeclaration().accept(this);

		f.getBody().accept(this);
		this.program.addFunction(fn);

		this.variables.endScope();
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
		this.functions.beginScope();

		this.program = new Program(this.filename);

		for (ast.Function f: p.getFunctions()) {
			f.accept(this);
		}

		this.functions.endScope();
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
		return v.getDeclaration().accept(this);
	}
}
