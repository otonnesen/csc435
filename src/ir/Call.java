package ir;

import java.util.ArrayList;

public class Call extends Operand {
	private String id;
	private ArrayList<Temp> args;

	public Call(String id, ArrayList<Temp> args) {
		this.id = id;
		this.args = args;
	}

	public String toString() {
		String call = "CALL " + this.id + "(";
		for (Temp a: this.args) {
			call += a.toString();
		}
		call += ")";
	}
}
