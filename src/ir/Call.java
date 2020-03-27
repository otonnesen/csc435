package ir;

import codegen.JasminVisitor;
import type.Type;

import java.util.ArrayList;

public class Call extends Operand {
	private String id;
	private ArrayList<Temp> args;

	public Call(Type type, String id, ArrayList<Temp> args) {
		super(type);
		this.id = id;
		this.args = args;
	}

	public String toString() {
		String call = "CALL " + this.id + "(";
		for (Temp a: this.args) {
			call += a.toString();
		}
		call += ")";
		return call;
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
