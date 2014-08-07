package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EqualTo extends BinaryExp implements Serializable {

	public EqualTo(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String basicAsString() {
		return getLeft().basicAsString() + " = " + getRight().basicAsString();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processEqualTo(this);
	}
	
}
