package com.booleanexp;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class IndexedValueRetrievalClosure implements Closure {
	
	private String nestedObjectReferences;
	private Context context;

	public IndexedValueRetrievalClosure(
			String anObjectReference,
			Context aContext) {
		this.nestedObjectReferences = anObjectReference;
		this.context = aContext;
	}

	@Override
	public Object evaluate() {
		StringTokenizer stringTokenizer = new java.util.StringTokenizer(nestedObjectReferences, ".");
		Object object = context.valueFor(stringTokenizer.nextToken());
		while (stringTokenizer.hasMoreTokens()) {
			Method aMethod = methodOfNamed(object, stringTokenizer.nextToken());
			try {
				object = aMethod.invoke(object);
			} catch (Exception e) {
				throw new RuntimeException("nested property configuration issue");
			}
		}
		return object;
	}

	private Method methodOfNamed(Object anObject, final String aName) {
		return (Method) CollectionUtils.find(Arrays.asList(anObject.getClass().getMethods()), new Predicate() {
			@Override
			public boolean evaluate(Object anObject) {
				return aName.equals(((Method) anObject).getName());
			}
		});
	}

	public static Closure toRetrieveOf(String anObjectReference, Context aContext) {
		return new IndexedValueRetrievalClosure(anObjectReference, aContext);
	}

}
