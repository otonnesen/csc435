package ir;

import java.util.ArrayList;

public class Call extends Instruction {
	private Temp recipient;
	private String func_name;
	private ArrayList<Temp> args;

	public Call(Temp recipient, String func_name, ArrayList<Temp> args) {
		this.recipient = recipient;
		this.func_name = func_name;
		this.args = args;
	}
	
	public String toString() {
		String call =  "CALL " + this.func_name + "(";
		for (Temp a: this.args) {
			call += a.toString();
		}
		call += ")";
		if (recipient != null) {
			return this.recipient.toString() + " :=" + call;
		} else {
			return call;
		}
	}
}
