package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AndExp extends BinaryExp implements Serializable {

	public AndExp(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	protected String asStringUnderAnd() {
		return basicAsString();
	}

	protected String basicAsString() {
		return getLeft().asStringUnderAnd() + " AND "
				+ getRight().asStringUnderAnd();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processAnd(this);
	}

}
