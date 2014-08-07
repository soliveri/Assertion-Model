package com.booleanexp;

import java.util.Comparator;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class DateComparator implements Comparator {

	public int compare(Object anObject, Object anotherObject) {
		Date aDate = (Date) anObject;
		Date anotherDate = (Date) anotherObject;
		if ((aDate).before(anotherDate)) {
			return -1;
		}
		if (aDate.getTime() == anotherDate.getTime()) {
			return 0;
		}
		return 1;
	}

}
