package ir;

import codegen.JasminVisitor;
import java.util.ArrayList;

public class CallInstruction extends Instruction {
	private Call call;

	public CallInstruction(Call call) {
		this.call = call;
	}

	public Call getCall() {
		return this.call;
	}

	public String toString() {
		return this.call.toString();
	}

	public void accept(JasminVisitor v) {
		v.visit(this);
	}
}
