package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BinaryExp extends Expression implements Serializable {

	public BinaryExp() {
		initialize();
	}

	public BinaryExp(Expression left, Expression right) {
		initialize();
		setLeft(left);
		setRight(right);
	}

	protected void initialize() {
		arguments = new Expression[2];
	}

	public Expression getLeft() {
		return arguments[0];
	}

	public void setLeft(Expression left) {
		arguments[0] = left;
	}

	public Expression getRight() {
		return arguments[1];
	}

	public void setRight(Expression right) {
		arguments[1] = right;
	}

	protected String asStringUnderAnd() {
		return "(" + basicAsString() + ")";
	}

	protected String asStringUnderOr() {
		return "(" + basicAsString() + ")";
	}

	protected String asStringUnderNot() {
		return "(" + basicAsString() + ")";
	}

}
