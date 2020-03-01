package ir;

public class Print extends Instruction {
	private Temp op;
	private boolean ln;

	public Print(Temp op, boolean ln) {
		this.op = op;
		this.ln = ln;
	}

	public String toString() {
		String s = this.op.getType().toString() + " " + this.op.toString();
		if (this.ln) {
			return "PRINTLN" + s;
		} else {
			return "PRINT" + s;
		}
	}
}
