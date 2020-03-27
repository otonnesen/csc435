package ir;

import codegen.JasminVisitor;

public abstract class Instruction {
	public abstract void accept(JasminVisitor v);
}
