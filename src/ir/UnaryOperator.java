package ir;

public enum UnaryOperator {
	ADDITIVE_INVERSE,
	LOGICAL_NEGATION;

	public String toString() {
		switch (this) {
			case ADDITIVE_INVERSE:
				return "-";
			case LOGICAL_NEGATION:
				return "!";
			default:
				return "?";
		}
	}
}
