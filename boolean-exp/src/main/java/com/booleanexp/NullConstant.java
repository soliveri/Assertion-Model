package com.booleanexp;

@SuppressWarnings("rawtypes")
public class NullConstant implements Comparable {
	
	@Override
	public int compareTo(Object anotherObject) {
		return anotherObject == null ? 0 : 1;
	}

}
