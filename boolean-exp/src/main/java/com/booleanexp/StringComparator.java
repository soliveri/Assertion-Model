package com.booleanexp;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class StringComparator implements Comparator {

	public int compare(Object anObject, Object anotherObject) {
		if (anObject == null && anotherObject == null) {
			return 0;
		} else if (anObject == null || anotherObject == null) {
			return -1;
		}
		return ((String) anObject).trim().compareToIgnoreCase(((String) anotherObject).trim());
	}

}
