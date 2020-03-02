package type;

public enum AtomicType {
	BOOLEAN,
	CHARACTER,
	FLOAT,
	INTEGER,
	STRING,
	VOID;

	public String getName() {
		switch(this) {
			case BOOLEAN:
				return "boolean";
			case CHARACTER:
				return "char";
			case FLOAT:
				return "float";
			case INTEGER:
				return "int";
			case STRING:
				return "string";
			case VOID:
				return "void";
			default:
				return "?";
		}
	}

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
			case VOID:
				return "V";
			default:
				return "?";
		}
	}
}
