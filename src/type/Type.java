package type;

public abstract class Type {
	private static String name;

	public abstract String toString();

	public boolean equals(Type other) {
		return this.getClass().equals(other.getClass());
	}

	public boolean comparable(Type other) {
		return this.getClass().isAssignableFrom(other.getClass()) || other.getClass().isAssignableFrom(this.getClass()) ;
	}
} 
