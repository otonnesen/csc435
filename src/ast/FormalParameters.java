package ast;
import java.util.ArrayList;

public class FormalParameters extends ASTNode {
	private ArrayList<Parameter> parameters;
	public FormalParameters() {
		this.parameters = new ArrayList<Parameter>();
	}

	public void addParameter(Parameter p) {
		this.parameters.add(p);
	}
}
