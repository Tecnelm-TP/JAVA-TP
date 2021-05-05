package TP5;

import java.io.Serializable;

public class Tag<T1, T2> implements Input<T1>, Output<T2>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	T1 input;
	T2 output;

	public Tag(T1 i, T2 o) {
		this.input = i;
		this.output = o;
	}

	public T1 getInput() {
		return this.input;
	}

	public T2 getOutput() {
		return this.output;
	}

	@Override
	public String toString() {
		return this.input + "/" + this.output;
	}
}