package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EndsWithExp extends BinaryExp implements Serializable {

	public EndsWithExp(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String basicAsString() {
		return getLeft().basicAsString() + " LIKE %"
				+ getRight().basicAsString();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processEndsWith(this);
	}
	
}
