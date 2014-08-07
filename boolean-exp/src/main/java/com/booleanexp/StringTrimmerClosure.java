package com.booleanexp;

public class StringTrimmerClosure implements Closure {
	
	private Object object;

	public StringTrimmerClosure(Object anObject) {
		this.object = anObject;
	}

	@Override
	public Object evaluate() {
		return ((String) object).trim();
	}

	public static Closure forValue(Object anObject) {
		return new StringTrimmerClosure(anObject);
	}

}
