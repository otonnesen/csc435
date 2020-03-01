package ir;

public enum BinaryOperator {
	ADDITION,
	SUBTRACTION,
	MULTIPLICATION,
	DIVISION,
	REMAINDER,
	LESS_THAN,
	LESS_EQUAL,
	EQUAL,
	NOT_EQUAL,
	GREATER_EQUAL,
	GREATER_THAN;

	public String toString() {
		switch (this) {
			case ADDITION:
				return "+";
			case SUBTRACTION:
				return "-";
			case MULTIPLICATION:
				return "*";
			case DIVISION:
				return "/";
			case REMAINDER:
				return "rem";
			case LESS_THAN:
				return "<";
			case LESS_EQUAL:
				return "<=";
			case EQUAL:
				return "==";
			case NOT_EQUAL:
				return "!=";
			case GREATER_EQUAL:
				return ">=";
			case GREATER_THAN:
				return ">";
			default:
				return "?";
		}
	}
}
