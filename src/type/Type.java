package type;

public abstract class Type {
	private static String name;

	public boolean equals(Type other) {
		return this.getClass().equals(other.getClass());
	}
}
