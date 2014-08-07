package com.booleanexp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FalseExp extends ConstantExp implements Serializable {

	public FalseExp() {
		super(new Boolean(false));
	}

	public Expression not() {
		return new TrueExp();
	}

	public Object value() {
		return new Boolean(false);
	}

	public boolean isFalse() {
		return true;
	}

	public Expression and(Expression expression) {
		return this;
	}

	public Expression or(Expression expression) {
		return expression;
	}

	@Override
	public Object ifTrueElse(Closure aClosure, Closure anotherClosure) {
		return anotherClosure.evaluate();
	}
	
}
