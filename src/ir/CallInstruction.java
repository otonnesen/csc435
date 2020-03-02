package ir;

import java.util.ArrayList;

public class CallInstruction extends Instruction {
	private Call call;

	public CallInstruction(Call call) {
		this.call = call;
	}

	public String toString() {
		return this.call.toString();
	}
}
