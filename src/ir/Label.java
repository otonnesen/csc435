package ir;

import codegen.JasminVisitor;

public class Label {
	private int number;

	public Label(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public String toString() {
		return "L" + this.number;
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
