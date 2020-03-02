package ir;

public class MethodType extends Type {
	private ArrayList<Type> params;

	public MethodType(Type ret, ArrayList<Type> params) {
		super(ret);
		this.params = params;
	}

	public ArrayList<Type> getParams() {
		return this.params;
	}

	public String toString() {
		String s = "(";
		for (Type p: this.params) {
			s += p.toString();
		}
		s += ")" + this.type.toString();
		return s;
	}
}
