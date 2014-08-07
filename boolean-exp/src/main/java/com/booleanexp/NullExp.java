package com.booleanexp;

@SuppressWarnings("serial")
public class NullExp extends Expression {

	private Expression expession;

	private NullExp(Expression anExpression) {
		this.expession = anExpression;
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processNullExp(this);
	}

	public Expression getValue() {
		return this.expession;
	}

	public static Expression forValue(Expression anExpression) {
		return new NullExp(anExpression);
	}

}
