package com.booleanexp;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Context implements Serializable {

	@SuppressWarnings("rawtypes")
	HashMap content;

	@SuppressWarnings("rawtypes")
	public Context() {
		content = new HashMap();
	}

	public Object valueFor(Object reference) {
		return content.get(reference);
	}

	@SuppressWarnings("unchecked")
	public void add(Object key, Object value) {
		content.put(key, value);
	}
	
}
