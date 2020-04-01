package ir;

import codegen.JasminVisitor;

public class Jump extends Instruction {
	private Temp cond;
	private Label label;

	public Jump(Temp cond, Label label) {
		this.cond = cond;
		this.label = label;
	}

	public Temp getCond() {
		return this.cond;
	}

	public Label getLabel() {
		return this.label;
	}

	public String toString() {
		if (cond != null) {
			return "IF " + this.cond.toString() + " GOTO " + this.label.toString();
		} else {
			return "GOTO " + this.label.toString();
		}
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
