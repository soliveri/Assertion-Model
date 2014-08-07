package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GreaterOrEqualTo extends BinaryExp implements Serializable {

	public GreaterOrEqualTo(Expression anExpression,
			Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String basicAsString() {
		return getLeft().basicAsString() + " >= " + getRight().basicAsString();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processGreaterOrEqualTo(this);
	}

}
