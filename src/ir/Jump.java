package ir;

public class Jump extends Instruction {
	private Temp cond;
	private Label label;

	public Jump(Temp cond, Label label) {
		this.cond = cond;
		this.label = label;
	}


	public String toString() {
		if (cond != null) {
			return "IF " + this.cond.toString() + " GOTO " + this.label.toString();
		} else {
			return "GOTO " + this.label.toString();
		}
	}
}
