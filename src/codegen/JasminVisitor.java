package codegen;

import ir.*;
import type.Type;
import type.AtomicType;
import type.TypeArray;

import java.lang.StringBuilder;

public class JasminVisitor {
	private StringBuilder out;
	private String className;
	private int label_num;

	private static Type BOOLEAN = new Type(AtomicType.BOOLEAN);
	private static Type CHARACTER = new Type(AtomicType.CHARACTER);
	private static Type FLOAT = new Type(AtomicType.FLOAT);
	private static Type INTEGER = new Type(AtomicType.INTEGER);
	private static Type STRING = new Type(AtomicType.STRING);
	private static Type VOID = new Type(AtomicType.VOID);

	public JasminVisitor(Program p) {
		this.out = new StringBuilder();
		this.className = p.getClassName();
		this.label_num = 0;
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

	public void visit(ArrayAccess a) {
		this.out.append("aload ");
		this.out.append(a.getId().getNumber());
		this.out.append("\n");

		this.out.append("iload ");
		this.out.append(a.getIndex().getNumber());
		this.out.append("\n");

		this.out.append("iaload\n");
	}
	public void visit(ArrayInit a) {
		this.out.append("ldc ");
		this.out.append(a.getSize());
		this.out.append("\n");
		Type t = a.getType();
		if (t.equals(BOOLEAN)) {
			this.out.append("newarray boolean");
		} else if (t.equals(CHARACTER)) {
			this.out.append("newarray char");
		} else if (t.equals(FLOAT)) {
			this.out.append("newarray float");
		} else if (t.equals(INTEGER)) {
			this.out.append("newarray int");
		} else if (t.equals(STRING)) {
			this.out.append("anewarray java/lang/String");
		}
		this.out.append("\n");
	}
	public void visit(Assignment a) {
		Operand l = a.getLeft();

		if (l instanceof ArrayAccess) {
			l = (ArrayAccess)l;
			this.out.append("aload ");
			this.out.append(((ArrayAccess)l).getId().getNumber());
			this.out.append("\n");
			this.out.append("iload ");
			this.out.append(((ArrayAccess)l).getIndex().getNumber());
			this.out.append("\n");
		}

		a.getRight().accept(this);

		if (l instanceof ArrayAccess) {
			this.out.append("iastore\n");
		} else {
			if (l.getType().equals(STRING) || l.getType() instanceof TypeArray) {
				this.out.append("astore ");
				this.out.append(((Temp)l).getNumber());
				this.out.append("\n");
			} else if (((Temp)l).getType().equals(FLOAT)) {
				this.out.append("fstore ");
				this.out.append(((Temp)l).getNumber());
				this.out.append("\n");
			} else {
				this.out.append("istore ");
				this.out.append(((Temp)l).getNumber());
				this.out.append("\n");
			}
		}
	}
	public void visit(BinaryOperation o) {
		o.getLeft().accept(this);
		o.getRight().accept(this);
		Type t = o.getType();
		String prefix;
		if (t.equals(STRING) || t instanceof TypeArray) {
			prefix = "a";
		} else if (t.equals(FLOAT)) {
			prefix = "f";
		} else {
			prefix = "i";
		}
		int l1;
		int l2;
		switch(o.getOp()) {
			case ADDITION:
				this.out.append(prefix + "add\n");
				break;
			case SUBTRACTION:
				this.out.append(prefix + "sub\n");
				break;
			case MULTIPLICATION:
				this.out.append(prefix + "mul\n");
				break;
			case LESS_THAN:
				l1 = this.label_num++;
				l2 = this.label_num++;
				if (t.equals(FLOAT)) {
					this.out.append("fcmpg\n");
				} else {
					this.out.append("isub\n");
				}
				this.out.append("iflt L_");
				this.out.append(l1);
				this.out.append("\n");

				this.out.append("ldc 0\n");

				this.out.append("goto L_");
				this.out.append(l2);
				this.out.append(":\n");

				this.out.append("L_");
				this.out.append(l1);
				this.out.append(":\n");

				this.out.append("ldc 1\n");

				this.out.append("L_");
				this.out.append(l2);
				this.out.append(":\n");
				break;
			case EQUAL:
				l1 = this.label_num++;
				l2 = this.label_num++;
				if (t.equals(FLOAT)) {
					this.out.append("fcmpg\n");
				} else {
					this.out.append("isub\n");
				}
				this.out.append("ifeq L_");
				this.out.append(l1);
				this.out.append("\n");

				this.out.append("ldc 0\n");

				this.out.append("goto L_");
				this.out.append(l2);
				this.out.append(":\n");

				this.out.append("L_");
				this.out.append(l1);
				this.out.append(":\n");

				this.out.append("ldc 1\n");

				this.out.append("L_");
				this.out.append(l2);
				this.out.append(":\n");
				break;
			default:
				this.out.append("");
				break;
		}
	}
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
			if (t.getType().equals(STRING)) {
				this.out.append("aload ");
			} else if (t.getType().equals(FLOAT)) {
				this.out.append("fload ");
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
	public void visit(ConstantBoolean c) {
		this.out.append("ldc ");
		this.out.append(c.getValue() ? "1" : "0");
		this.out.append("\n");
	}
	public void visit(ConstantCharacter c) {
		this.out.append("ldc ");
		this.out.append((int)c.getValue());
		this.out.append("\n");
	}
	public void visit(ConstantFloat c) {
		this.out.append("ldc ");
		this.out.append(c.getValue());
		this.out.append("\n");
	}
	public void visit(ConstantInteger c) {
		this.out.append("ldc ");
		this.out.append(c.getValue());
		this.out.append("\n");
	}
	public void visit(ConstantString c) {
		this.out.append("ldc ");
		this.out.append(c.getValue());
		this.out.append("\n");
	}
	public void visit(Function f) {
		int start = this.label_num++;
		int end = this.label_num++;
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
		this.out.append("L_");
		this.out.append(start);
		this.out.append(":\n");

		for (Temp t: f.getTemps()) {
			if (t.getCls() != Temp.tempClass.PARAM) {
				if (t.getType().equals(STRING) || t.getType() instanceof TypeArray) {
					this.out.append("aconst_null\n");
					this.out.append("astore ");
					this.out.append(t.getNumber());
					this.out.append("\n");
				} else if (t.getType().equals(FLOAT)) {
					this.out.append("ldc 0.0\n");
					this.out.append("fstore ");
					this.out.append(t.getNumber());
					this.out.append("\n");
				} else {
					this.out.append("ldc 0\n");
					this.out.append("istore ");
					this.out.append(t.getNumber());
					this.out.append("\n");
				}
			}
		}

		this.out.append("\n");

		for (Instruction i: f.getInstructions()) {
			i.accept(this);
		}

		this.out.append("L_");
		this.out.append(end);
		this.out.append(":\n");
		this.out.append(".end method\n");
	}
	public void visit(Jump i) {}
	public void visit(Label i) {}
	public void visit(LabelInstruction i) {}
	public void visit(Print i) {}
	public void visit(Program p) {
		this.out.append(".class public ");
		this.out.append(p.getClassName());
		this.out.append("\n.super java/lang/Object\n\n");
		for (Function f: p.getFunctions()) {
			f.accept(this);
		}
	}
	public void visit(Return r) {
		if (r.getRetVal() != null) {
			Type t = r.getRetVal().getType();
			if (t.equals(STRING) || t instanceof TypeArray) {
				this.out.append("aload ");
				this.out.append(r.getRetVal().getNumber());
				this.out.append("\n");
				this.out.append("areturn\n");
			} else if (t.equals(FLOAT)) {
				this.out.append("fload ");
				this.out.append(r.getRetVal().getNumber());
				this.out.append("\n");
				this.out.append("freturn\n");
			} else {
				this.out.append("iload ");
				this.out.append(r.getRetVal().getNumber());
				this.out.append("\n");
				this.out.append("ireturn\n");
			}
		} else {
			this.out.append("return\n");
		}
	}
	public void visit(Temp t) {
		if (t.getType().equals(STRING)) {
			this.out.append("aload ");
			this.out.append(t.getNumber());
			this.out.append("\n");
		} else if (t.getType().equals(FLOAT)) {
			this.out.append("fload ");
			this.out.append(t.getNumber());
			this.out.append("\n");
		} else {
			this.out.append("iload ");
			this.out.append(t.getNumber());
			this.out.append("\n");
		}
	}
	public void visit(UnaryOperation o) {
		switch (o.getOp()) {
			case LOGICAL_NEGATION:
				o.getTemp().accept(this);
				this.out.append("ldc 1\n");
				this.out.append("ixor\n");
				break;
			default:
				break;
		}
	}
}
