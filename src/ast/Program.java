package ast;
import java.util.ArrayList;

public class Program {
	private ArrayList<Function> functions;

	public Program() {
		this.functions = new ArrayList<Function>();
	}

	public void addFunction(Function f) {
		this.functions.add(f);
	}
}
