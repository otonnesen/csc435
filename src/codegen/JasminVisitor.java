package codegen;

import ir.*;

import java.lang.StringBuilder;

public class JasminVisitor {
	private Program irProg;
	private StringBuilder out;

	public JasminVisitor(Program p) {
		irProg.accept(this);
		this.out = new StringBuilder();
	}

	public void visit(ArrayAccess i) {}
	public void visit(ArrayInit i) {}
	public void visit(Assignment i) {}
	public void visit(BinaryOperation i) {}
	public void visit(Call i) {}
	public void visit(CallInstruction i) {}
	public void visit(ConstantBoolean i) {}
	public void visit(ConstantCharacter i) {}
	public void visit(ConstantFloat i) {}
	public void visit(ConstantInteger i) {}
	public void visit(ConstantString i) {}
	public void visit(Function i) {}
	public void visit(Jump i) {}
	public void visit(Label i) {}
	public void visit(LabelInstruction i) {}
	public void visit(MethodType i) {}
	public void visit(Print i) {}
	public void visit(Program i) {}
	public void visit(Return i) {}
	public void visit(Temp i) {}
	public void visit(UnaryOperation i) {}
}
