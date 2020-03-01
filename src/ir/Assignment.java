package ir;

public class Assignment extends Instruction {
	private Operand left;
	private Operand right;

	public Assignment(Operand left, Operand right) {
		assert (left instanceof Temp || left instanceof ArrayAccess);
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return left.toString() + " := " + right.toString();
	}
}
