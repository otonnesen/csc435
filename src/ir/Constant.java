package ir;

import codegen.JasminVisitor;
import type.Type;

public abstract class Constant extends Operand {
	public Constant(Type t) {
		super(t);
	}

	public abstract void accept(JasminVisitor v);
}
