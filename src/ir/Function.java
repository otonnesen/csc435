package ir;

import java.util.ArrayList;

public class Function {
	private String name;
	private String sig;
	private ArrayList<Temp> temps;
	private ArrayList<Instruction> instructions;

	public Function(String name, String sig) {
		this.name = name;
		this.sig = sig;
		this.temps = new ArrayList<Temp>();
		this.instructions = new ArrayList<Instruction>();
	}

	public String getName() {
		return this.name;
	}

	public String getSig() {
		return this.sig;
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
		String s = "FUNC " + this.name + " " + this.sig + "{";
		for (Temp t: this.temps) {
			s += "TEMP " + t.toString() + ":" + t.getType().toString() + ";";
		}
		for (Instruction i: this.instructions) {
			s += i.toString() + ";";
		}
		s += "}";
		return s;
	}
}
