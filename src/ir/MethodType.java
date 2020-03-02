package ir;

import type.Type;

import java.util.ArrayList;

public class MethodType {
	private Type ret;
	private ArrayList<Type> params;

	public MethodType(Type ret, ArrayList<Type> params) {
		this.ret = ret;
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
		s += ")" + this.ret.toString();
		return s;
	}
}
