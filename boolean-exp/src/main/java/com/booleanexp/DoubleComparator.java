package com.booleanexp;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class DoubleComparator implements Comparator {

	@Override
	public int compare(Object anObject, Object anotherObject) {
		Double aDobule = (Double) anObject;
		Double anotherDouble = (Double) anotherObject;
		return aDobule.compareTo(anotherDouble);
	}

}
