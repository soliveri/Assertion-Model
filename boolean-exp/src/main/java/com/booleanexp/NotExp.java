package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NotExp extends Expression implements Serializable {

	public NotExp(Expression expression) {
		initialize();
		arguments[0] = expression;
	}

	protected void initialize() {
		arguments = new Expression[1];
	}

	protected String basicAsString() {
		return "NOT " + internalExp().asStringUnderNot();
	}

	public Expression internalExp() {
		return arguments[0];
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processNot(this);
	}

}
