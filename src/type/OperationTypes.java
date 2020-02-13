package type;

import java.util.Arrays;
import java.util.HashSet;

public final class OperationTypes {
	// These are sets indicating which types can be used with which operations.
	public static final HashSet<Type> minus = new HashSet<Type>(
			Arrays.asList(
				TypeInteger.getInstance(),
				TypeFloat.getInstance(),
				TypeCharacter.getInstance()
			));
	public static final HashSet<Type> plus = new HashSet<Type>(
			Arrays.asList(
				TypeInteger.getInstance(),
				TypeFloat.getInstance(),
				TypeCharacter.getInstance(),
				TypeString.getInstance()
			));
	public static final HashSet<Type> times = new HashSet<Type>(
			Arrays.asList(
				TypeInteger.getInstance(),
				TypeFloat.getInstance()
			));
	public static final HashSet<Type> lessThan = new HashSet<Type>(
			Arrays.asList(
				TypeInteger.getInstance(),
				TypeFloat.getInstance(),
				TypeCharacter.getInstance(),
				TypeString.getInstance(),
				TypeBoolean.getInstance()
			));
	public static final HashSet<Type> equals = new HashSet<Type>(
			Arrays.asList(
				TypeInteger.getInstance(),
				TypeFloat.getInstance(),
				TypeCharacter.getInstance(),
				TypeString.getInstance(),
				TypeBoolean.getInstance()
			));
}
