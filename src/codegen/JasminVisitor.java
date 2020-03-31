package codegen;

import ir.*;
import type.Type;
import type.AtomicType;
import type.TypeArray;

import java.lang.StringBuilder;

public class JasminVisitor {
	private StringBuilder out;
	private String className;

	private static Type BOOLEAN = new Type(AtomicType.BOOLEAN);
	private static Type CHARACTER = new Type(AtomicType.CHARACTER);
	private static Type FLOAT = new Type(AtomicType.FLOAT);
	private static Type INTEGER = new Type(AtomicType.INTEGER);
	private static Type STRING = new Type(AtomicType.STRING);
	private static Type VOID = new Type(AtomicType.VOID);

	public JasminVisitor(Program p) {
		this.out = new StringBuilder();
		this.className = p.getClassName();
		p.accept(this);
		this.out.append(
				";--------------------------------------------;\n"
				+ ";                                            ;\n"
				+ "; Boilerplate                                ;\n"
				+ ";                                            ;\n"
				+ ";--------------------------------------------;\n"
				+ "\n"
				+ ".method public static main([Ljava/lang/String;)V\n"
				+ "; set limits used by this method\n"
				+ ".limit locals 1\n"
				+ ".limit stack 4\n"
				+ "invokestatic test/__main()V\n"
				+ "return\n"
				+ ".end method\n"
				+ "\n"
				+ "; standard initializer\n"
				+ ".method public <init>()V\n"
				+ "aload_0\n"
				+ "invokenonvirtual java/lang/Object/<init>()V\n"
				+ "return\n"
				+ ".end method\n");
	}

	public String toString() {
		return this.out.toString();
	}

	public void visit(ArrayAccess i) {}
	public void visit(ArrayInit i) {}
	public void visit(Assignment i) {}
	public void visit(BinaryOperation i) {}
	public void visit(Call c) {
		this.out.append(c.getId());
		this.out.append("(");
		for (Temp t: c.getArgs()) {
			this.out.append(t.getType().toString());
		}
		this.out.append(")");
		this.out.append(c.getType().toString());
	}
	public void visit(CallInstruction i) {
		for (Temp t: i.getCall().getArgs()) {
			if (t.getType().equals(FLOAT)) {
				this.out.append("fload ");
			} else if (t.getType().equals(STRING)) {
				this.out.append("aload ");
			} else {
				this.out.append("iload ");
			}
			this.out.append(t.getNumber());
			this.out.append("\n");
		}
		this.out.append("invokestatic ");
		this.out.append(this.className);
		this.out.append("/");
		i.getCall().accept(this);
	}
	public void visit(ConstantBoolean i) {}
	public void visit(ConstantCharacter i) {}
	public void visit(ConstantFloat i) {}
	public void visit(ConstantInteger i) {}
	public void visit(ConstantString i) {}
	public void visit(Function f) {
		this.out.append(".method public static ");
		if (f.getName().equals("main")
				&& f.getType().getType().equals(VOID)
				&& f.getType().getParams().size() == 0) {
			this.out.append("__");
				}
		this.out.append(f.getName());
		this.out.append("(");
		for (Type t: f.getType().getParams()) {
			if (t instanceof TypeArray) {
				this.out.append("[");
				this.out.append(t.getAtomicType().toString());

			} else {
				this.out.append(t.toString());
			}
		}
		this.out.append(")");
		this.out.append(f.getType().getType());

		this.out.append("\n");

		this.out.append(".limit locals ");
		this.out.append(f.getTemps().size());
		this.out.append("\n");

		this.out.append(".limit stack 16\n");

		for (Instruction i: f.getInstructions()) {
			i.accept(this);
			out.append("\n");
		}

		this.out.append(".end method\n");
	}
	public void visit(Jump i) {}
	public void visit(Label i) {}
	public void visit(LabelInstruction i) {}
	public void visit(MethodType i) {}
	public void visit(Print i) {}

	public void visit(Program p) {
		this.out.append(".class public ");
		this.out.append(p.getClassName());
		this.out.append("\n.super java/lang/Object\n\n");
		for (Function f: p.getFunctions()) {
			f.accept(this);
		}
	}
	public void visit(Return i) {}
	public void visit(Temp i) {}
	public void visit(UnaryOperation i) {}
}
