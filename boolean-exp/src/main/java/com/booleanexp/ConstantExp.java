package com.booleanexp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class ConstantExp extends Expression implements Serializable {

	Object value;

	public ConstantExp(Object aValue) {
		this.value = aValue;
	}

	public static ConstantExp forValue(Object aValue) {
		if (aValue == null) {
			return new ConstantExp(new NullConstant());
		} else if (Boolean.class.isAssignableFrom(aValue.getClass())) {
			if ((Boolean) aValue) {
				return new TrueExp();
			}
			return new FalseExp();
		}
		return new ConstantExp(aValue);
	}

	public Object getValue() {
		return value;
	}
	
	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processConstant(this);
	}
	
	public Expression and(Expression expression) {
		return super.and(expression);
	}

	public Expression or(Expression expression) {
		return super.or(expression);
	}

	protected String basicAsString() {
		return value.toString();
	}

	public ConstantExp isConstant() {
		return new TrueExp();
	}

	@SuppressWarnings("rawtypes")
	protected Set variables() {
		return new HashSet();
	}

	public ConstantExp isNull() {
		return ConstantExp.forValue(value.getClass().equals(NullConstant.class));
	}
	
}
