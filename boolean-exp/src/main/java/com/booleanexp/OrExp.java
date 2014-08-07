package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrExp extends BinaryExp implements Serializable {

	public OrExp(Expression expression, Expression expression1) {
		super(expression, expression1);
	}

	protected String asStringUnderOr() {
		return basicAsString();
	}

	protected String basicAsString() {
		return getLeft().asStringUnderOr() + " OR "
				+ getRight().asStringUnderOr();
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processOr(this);
	}
	
}
