package ast;
import visitor.Visitor;
import java.util.ArrayList;

public class FunctionBody extends ASTNode {
	private ArrayList<VariableDeclaration> variables;
	private ArrayList<Statement> statements;

	public FunctionBody() {
		variables = new ArrayList<VariableDeclaration>();
		statements = new ArrayList<Statement>();
	}

	public void addVariableDeclaration(VariableDeclaration vd) {
		this.variables.add(vd);
	}

	public void addStatement(Statement s) {
		this.statements.add(s);
	}

	public ArrayList<VariableDeclaration> getVariables() {
		return this.variables;
	}

	public ArrayList<Statement> getStatements() {
		return this.statements;
	}

	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
