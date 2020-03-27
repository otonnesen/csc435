package ir;

import codegen.JasminVisitor;

public class Return extends Instruction {
	private Temp retval;

	public Return(Temp retval) {
		this.retval = retval;
	}

	public String toString() {
		if (retval != null) {
			return "RETURN " + retval.toString();
		} else {
			return "RETURN";
		}
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
