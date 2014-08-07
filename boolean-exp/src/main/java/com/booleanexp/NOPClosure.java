package com.booleanexp;

public class NOPClosure implements Closure {
	
	private Object object;

	public NOPClosure(Object anObject) {
		this.object = anObject;
	}

	@Override
	public Object evaluate() {
		return object;
	}

	public static Closure forValue(Object anObject) {
		return new NOPClosure(anObject);
	}
	
}
