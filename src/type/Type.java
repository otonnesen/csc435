package type;

public abstract class Type {
	private String name;

	public abstract String toString();

	public abstract boolean isComparable(Type t);
}
