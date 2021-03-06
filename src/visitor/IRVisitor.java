package visitor;

import ir.*;
import type.Type;
import type.TypeArray;
import type.AtomicType;
import environment.Environment;

import java.util.ArrayList;

public class IRVisitor extends Visitor<Operand> {
	private Environment<String, Type> functions;
	private Environment<String, Temp> variables;
	private Program program;
	private TempFactory tf;
	private int nextLabel;
	private Function curFunc;
	private String filename;

	private static Temp.tempClass PARAM = Temp.tempClass.PARAM;
	private static Temp.tempClass LOCAL = Temp.tempClass.LOCAL;
	private static Temp.tempClass TEMP = Temp.tempClass.TEMP;
	private static Type BOOLEAN = new Type(AtomicType.BOOLEAN);
	private static Type CHARACTER = new Type(AtomicType.CHARACTER);
	private static Type FLOAT = new Type(AtomicType.FLOAT);
	private static Type INTEGER = new Type(AtomicType.INTEGER);
	private static Type STRING = new Type(AtomicType.STRING);
	private static Type VOID = new Type(AtomicType.VOID);

	public Program getProgram() {
		return this.program;
	}

	private Temp getNextTemp(Type type, Temp.tempClass cls) {
		Temp t = tf.get(type, cls);
		this.curFunc.addTemp(t);
		return t;
	}

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

	public Operand visit(ast.Block b) {
		for (ast.Statement s: b.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Operand visit(ast.Declaration d) {
		Temp t = this.getNextTemp(d.getType(), LOCAL);
		this.variables.put(d.getId().getId(), t);
		if (d.getType() instanceof TypeArray) {
			TypeArray ta = (TypeArray)d.getType();
			// yuck.
			Instruction i = new Assignment(t,
					new ArrayInit(new Type(d.getType().getAtomicType()),
						ta.getSize()));
			this.curFunc.addInstruction(i);
		}
		return t;
	}
	public Operand visit(ast.ExpressionArrayAccess e) {
		Temp id = (Temp)e.getId().accept(this);
		Temp index = (Temp)e.getExpr().accept(this);
		Operand a = new ArrayAccess(id, index);
		return a;
	}
	public Operand visit(ast.ExpressionFunctionCall e) {
		String id = e.getId();
		Type type = this.functions.lookup(id);
		ArrayList<Temp> args = new ArrayList<Temp>();
		for (ast.Expression p: e.getExprList()) {
			args.add((Temp)p.accept(this));
		}
		Call call = new Call(type, id, args);
		if (type.equals(VOID)) {
			this.curFunc.addInstruction(new CallInstruction(call));
			return null;
		} else {
			Temp t = this.getNextTemp(type, TEMP);
			this.curFunc.addInstruction(new Assignment(t, call));
			return t;
		}
	}
	public Operand visit(ast.ExpressionIdentifier e) {
		return this.variables.lookup(e.getId());
	}
	public Operand visit(ast.ExpressionIsEqual e) {
		Temp lhs = (Temp)e.getLeftExpr().accept(this);
		Temp rhs = (Temp)e.getRightExpr().accept(this);
		Operand o = new BinaryOperation(lhs.getType().getAtomicType(), lhs,
				rhs, BinaryOperator.EQUAL);
		Temp t = this.getNextTemp(BOOLEAN, TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.ExpressionLessThan e) {
		Temp lhs = (Temp)e.getLeftExpr().accept(this);
		Temp rhs = (Temp)e.getRightExpr().accept(this);
		Operand o = new BinaryOperation(lhs.getType().getAtomicType(), lhs,
				rhs, BinaryOperator.LESS_THAN);
		Temp t = this.getNextTemp(BOOLEAN, TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.ExpressionMinus e) {
		Temp lhs = (Temp)e.getLeftExpr().accept(this);
		Temp rhs = (Temp)e.getRightExpr().accept(this);
		Operand o = new BinaryOperation(lhs.getType().getAtomicType(), lhs,
				rhs, BinaryOperator.SUBTRACTION);
		Temp t = this.getNextTemp(lhs.getType(), TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.ExpressionParenthesis e) {
		return e.getExpr().accept(this);
	}
	public Operand visit(ast.ExpressionPlus e) {
		Temp lhs = (Temp)e.getLeftExpr().accept(this);
		Temp rhs = (Temp)e.getRightExpr().accept(this);
		Operand o = new BinaryOperation(lhs.getType().getAtomicType(), lhs,
				rhs, BinaryOperator.ADDITION);
		Temp t = this.getNextTemp(lhs.getType(), TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.ExpressionTimes e) {
		Temp lhs = (Temp)e.getLeftExpr().accept(this);
		Temp rhs = (Temp)e.getRightExpr().accept(this);
		Operand o = new BinaryOperation(lhs.getType().getAtomicType(), lhs,
				rhs, BinaryOperator.MULTIPLICATION);
		Temp t = this.getNextTemp(lhs.getType(), TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.FunctionBody fb) {
		for (ast.VariableDeclaration vd: fb.getVariables()) {
			vd.accept(this);
		}

		for (ast.Statement s: fb.getStatements()) {
			s.accept(this);
		}
		return null;
	}
	public Operand visit(ast.FunctionDeclaration fd) {
		this.functions.put(fd.getId(), fd.getType());
		for (ast.Declaration p: fd.getParameters()) {
			Temp t = this.getNextTemp(p.getType(), PARAM);
			this.variables.put(p.getId().getId(), t);
		}
		return null;
	}
	public Operand visit(ast.Function f) {
		this.variables.beginScope();
		this.tf = new TempFactory();
		this.nextLabel = 0;

		MethodType type = this.getSig(f);
		Function fn = new Function(f.getDeclaration().getId(), type);
		this.curFunc = fn;

		f.getDeclaration().accept(this);

		f.getBody().accept(this);

		if (type.getType().equals(VOID)) {
			Instruction i = new Return(null);
			this.curFunc.addInstruction(i);
		}
		this.program.addFunction(fn);

		this.variables.endScope();
		return null;
	}
	public Operand visit(ast.LiteralBoolean l) {
		Temp t = this.getNextTemp(BOOLEAN, TEMP);
		Operand o = new ConstantBoolean(l.getValue());
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.LiteralCharacter l) {
		Temp t = this.getNextTemp(CHARACTER, TEMP);
		Operand o = new ConstantCharacter(l.getValue());
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.LiteralFloat l) {
		Temp t = this.getNextTemp(FLOAT, TEMP);
		Operand o = new ConstantFloat(l.getValue());
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.LiteralInteger l) {
		Temp t = this.getNextTemp(INTEGER, TEMP);
		Operand o = new ConstantInteger(l.getValue());
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.LiteralString l) {
		Temp t = this.getNextTemp(STRING, TEMP);
		Operand o = new ConstantString(l.getValue());
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.Program p) {
		this.functions.beginScope();

		for (ast.Function f: p.getFunctions()) {
			this.functions.put(f.getDeclaration().getId(), f.getDeclaration().getType());
		}

		this.program = new Program(this.filename);

		for (ast.Function f: p.getFunctions()) {
			f.accept(this);
		}

		this.functions.endScope();
		return null;
	}
	public Operand visit(ast.StatementArrayAssignment s) {
		Operand eaa = s.getArrayAccess().accept(this);
		Operand o = s.getExpr().accept(this);
		if (o instanceof ArrayAccess) {
			Temp t = this.getNextTemp(o.getType(), TEMP);
			Instruction i = new Assignment(t, o);
			o = t;
		}
		Instruction i = new Assignment(eaa, o);
		this.curFunc.addInstruction(i);
		return eaa;
	}
	public Operand visit(ast.StatementAssign s) {
		Temp t = this.variables.lookup(s.getId().getId());
		Operand o = s.getExpr().accept(this);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		return t;
	}
	public Operand visit(ast.StatementEmpty s) {
		return null;
	}
	public Operand visit(ast.StatementExpression s) {
		return s.getExpr().accept(this);
	}
	public Operand visit(ast.StatementIf s) {
		Label skipLabel = new Label(this.nextLabel++);
		Temp t = (Temp)s.getExpr().accept(this);
		Temp t_inv = this.getNextTemp(BOOLEAN, TEMP);
		Operand negate = new UnaryOperation(AtomicType.BOOLEAN, t,
				UnaryOperator.LOGICAL_NEGATION);
		Instruction i = new Assignment(t_inv, negate);
		this.curFunc.addInstruction(i);
		i = new Jump(t_inv, skipLabel);
		this.curFunc.addInstruction(i);
		s.getIfBlock().accept(this);
		if (s.getElseBlock() == null) {
			this.curFunc.addInstruction(new LabelInstruction(skipLabel));
		} else {
			Label skipEnd = new Label(this.nextLabel++);
			i = new Jump(null, skipEnd);
			this.curFunc.addInstruction(i);
			this.curFunc.addInstruction(new LabelInstruction(skipLabel));
			s.getElseBlock().accept(this);
			this.curFunc.addInstruction(new LabelInstruction(skipEnd));
		}
		return null;
	}
	public Operand visit(ast.StatementPrint s) {
		Operand o = s.getExpr().accept(this);
		Temp t = this.getNextTemp(o.getType(), TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		i = new Print(t, false);
		this.curFunc.addInstruction(i);
		return null;
	}
	public Operand visit(ast.StatementPrintln s) {
		Operand o = s.getExpr().accept(this);
		Temp t = this.getNextTemp(o.getType(), TEMP);
		Instruction i = new Assignment(t, o);
		this.curFunc.addInstruction(i);
		i = new Print(t, true);
		this.curFunc.addInstruction(i);
		return null;
	}
	public Operand visit(ast.StatementReturn s) {
		Type type = this.curFunc.getType().getType();
		Instruction i;
		if (type.equals(VOID)) {
			i = new Return(null);
		} else {
			i = new Return((Temp)s.getExpr().accept(this));
		}
		this.curFunc.addInstruction(i);
		return null;
	}
	public Operand visit(ast.StatementWhile s) {
		Label start = new Label(this.nextLabel++);
		Label end = new Label(this.nextLabel++);

		Instruction i = new LabelInstruction(start);
		this.curFunc.addInstruction(i);
		Temp t = (Temp)s.getExpr().accept(this);
		Temp t_inv = this.getNextTemp(BOOLEAN, TEMP);
		Operand negate = new UnaryOperation(AtomicType.BOOLEAN, t,
				UnaryOperator.LOGICAL_NEGATION);
		i = new Assignment(t_inv, negate);
		this.curFunc.addInstruction(i);
		i = new Jump(t_inv, end);
		this.curFunc.addInstruction(i);
		s.getBlock().accept(this);
		i = new Jump(null, start);
		this.curFunc.addInstruction(i);
		i = new LabelInstruction(end);
		this.curFunc.addInstruction(i);
		return null;
	}
	public Operand visit(ast.VariableDeclaration v) {
		return v.getDeclaration().accept(this);
	}
}
