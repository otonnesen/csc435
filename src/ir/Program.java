package ir;

import java.util.ArrayList;

public class Program {
	private String className;
	private ArrayList<Function> functions;

	public Program(String className) {
		this.className = className;
		this.functions = new ArrayList<Function>();
	}

	public String getClassName() {
		return this.className;
	}

	public ArrayList<Function> getFunctions() {
		return this.functions;
	}

	public void addFunction(Function f) {
		this.functions.add(f);
	}

	public String toString() {
		String s = "PROG " + this.className + "\n";
		String spc = "";
		for (Function f: this.functions) {
			s += spc;
			spc = "\n";
			s += f.toString();
		}
		return s;
	}
}
