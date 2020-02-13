package type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

import ast.ExpressionIsEqual;
import ast.ExpressionLessThan;
import ast.ExpressionMinus;
import ast.ExpressionPlus;
import ast.ExpressionTimes;

public final class OperationTypes {
	public static final HashMap<String, HashSet<Type>> opTypes;
	static {
		// These are sets indicating which types can be used with which
		// operations.
		HashSet<Type> equals = new HashSet<Type>(
				Arrays.asList(
					TypeInteger.getInstance(),
					TypeFloat.getInstance(),
					TypeCharacter.getInstance(),
					TypeString.getInstance(),
					TypeBoolean.getInstance()
				));
		HashSet<Type> lessThan = new HashSet<Type>(
				Arrays.asList(
					TypeInteger.getInstance(),
					TypeFloat.getInstance(),
					TypeCharacter.getInstance(),
					TypeString.getInstance(),
					TypeBoolean.getInstance()
				));
		HashSet<Type> minus = new HashSet<Type>(
				Arrays.asList(
					TypeInteger.getInstance(),
					TypeFloat.getInstance(),
					TypeCharacter.getInstance()
				));
		HashSet<Type> plus = new HashSet<Type>(
				Arrays.asList(
					TypeInteger.getInstance(),
					TypeFloat.getInstance(),
					TypeCharacter.getInstance(),
					TypeString.getInstance()
				));
		HashSet<Type> times = new HashSet<Type>(
				Arrays.asList(
					TypeInteger.getInstance(),
					TypeFloat.getInstance()
				));

		opTypes = new HashMap<String, HashSet<Type>>();
		opTypes.put(ExpressionIsEqual.op, equals);
		opTypes.put(ExpressionLessThan.op, lessThan);
		opTypes.put(ExpressionMinus.op, minus);
		opTypes.put(ExpressionPlus.op, plus);
		opTypes.put(ExpressionTimes.op, times);
	}
}
