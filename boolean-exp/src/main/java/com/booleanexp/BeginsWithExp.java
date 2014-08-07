package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BeginsWithExp extends BinaryExp implements Serializable {

	public BeginsWithExp(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String basicAsString() {
		return getLeft().basicAsString() + " LIKE "
				+ getRight().basicAsString() + "%";
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processBeginsWith(this);
	}

}
