package ir;

import codegen.JasminVisitor;

public class LabelInstruction extends Instruction {
	private Label label;

	public LabelInstruction(Label label) {
		this.label = label;
	}

	public String toString() {
		return "L" + this.label.getNumber() + ":";
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
