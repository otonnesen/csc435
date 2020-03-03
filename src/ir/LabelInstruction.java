package ir;

public class LabelInstruction extends Instruction {
	private Label label;

	public LabelInstruction(Label label) {
		this.label = label;
	}

	public String toString() {
		return "L" + this.label.getNumber() + ":";
	}
}
