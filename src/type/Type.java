package type;

public abstract class Type {
	private static String name;

	public boolean equals(Type other) {
		return this.getClass().equals(other.getClass());
	}

	public boolean comparable(Type other) {
		return this.subtype(other) || this.supertype(other);
	}

	public boolean subtype(Type other) {
		// Returns true if this is a subtype of other
		return other.getClass().isAssignableFrom(this.getClass());
	}

	public boolean supertype(Type other) {
		// Returns true if this is a supertype of other
		return this.getClass().isAssignableFrom(other.getClass());
	}
}
