package com.booleanexp;


@SuppressWarnings("serial")
public class InExp extends BinaryExp {
	
	public InExp(Expression anExpression, Expression anotherExpression) {
		super(anExpression, anotherExpression);
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processIn(this);
	}

}
