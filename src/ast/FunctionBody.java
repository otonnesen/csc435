package ast;
import java.util.ArrayList;

public class FunctionBody extends ASTNode {
	private ArrayList<VariableDeclaration> variableDeclarations;
	private ArrayList<Statement> statements;

	public FunctionBody() {
		variableDeclarations = new ArrayList<VariableDeclaration>();
		statements = new ArrayList<Statement>();
	}

	public void addVariableDeclaration(VariableDeclaration vd) {
		this.variableDeclarations.add(vd);
	}

	public void addStatement(Statement s) {
		this.statements.add(s);
	}
}
