package ir;

public enum AtomicType {
	BOOLEAN,
	CHARACTER,
	FLOAT,
	INTEGER,
	STRING;

	public String toString() {
		switch(this) {
			case BOOLEAN:
				return "Z";
			case CHARACTER:
				return "C";
			case FLOAT:
				return "F";
			case INTEGER:
				return "I";
			case STRING:
				return "U";
			default:
				return "?";
		}
	}
}
