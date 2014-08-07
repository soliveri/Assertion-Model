package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrueExp extends ConstantExp implements Serializable {

	public TrueExp() {
		super(new Boolean(true));
	}

	public Expression not() {
		return new FalseExp();
	}

	public Object value() {
		return new Boolean(true);
	}

	public boolean isTrue() {
		return true;
	}

	public Expression and(Expression expression) {
		return expression;
	}

	public Expression or(Expression expression) {
		return this;
	}

	@Override
	public Object ifTrueElse(Closure aClosure, Closure anotherClosure) {
		return aClosure.evaluate();
	}
	
}
