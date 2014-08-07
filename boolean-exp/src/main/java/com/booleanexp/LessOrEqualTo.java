package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LessOrEqualTo extends BinaryExp implements Serializable {

	public LessOrEqualTo(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String basicAsString() {
		return getLeft().basicAsString() + " <= " + getRight().basicAsString();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processLessOrEqualTo(this);
	}
	
}
