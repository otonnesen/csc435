package ir;

import codegen.JasminVisitor;
import java.util.ArrayList;

public class Function {
	private String name;
	private MethodType type;
	private ArrayList<Temp> temps;
	private ArrayList<Instruction> instructions;

	public Function(String name, MethodType type) {
		this.name = name;
		this.type = type;
		this.temps = new ArrayList<Temp>();
		this.instructions = new ArrayList<Instruction>();
	}

	public String getName() {
		return this.name;
	}

	public MethodType getType() {
		return this.type;
	}

	public ArrayList<Temp> getTemps() {
		return this.temps;
	}

	public void addTemp(Temp t) {
		this.temps.add(t);
	}

	public ArrayList<Instruction> getInstructions() {
		return this.instructions;
	}

	public void addInstruction(Instruction i) {
		this.instructions.add(i);
	}

	public String toString() {
		// TODO: StringBuilder
		String s = "FUNC " + this.name + " " + this.type.toString() + "\n{\n";
		String spc = "";
		for (Temp t: this.temps) {
			s += "TEMP " + t.getNumber() + ":" + t.getType().toString() + ";\n";
			spc = "\n";
		}
		s += spc;
		for (Instruction i: this.instructions) {
			s += i.toString() + ";\n";
		}
		s += "}";
		return s;
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
