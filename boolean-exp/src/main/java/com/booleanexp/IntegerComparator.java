package com.booleanexp;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class IntegerComparator implements Comparator {

	public int compare(Object anInteger, Object anotherInteger) {
		return ((Integer) anInteger).compareTo((Integer) anotherInteger);
	}

}
