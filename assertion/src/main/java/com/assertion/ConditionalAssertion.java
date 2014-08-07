package com.assertion;

import java.util.List;

import com.booleanexp.Context;


public class ConditionalAssertion implements Assertion {

	private Predicate predicate;
	private List<Assertion> ifAssertions;
	private List<Assertion> elseAssertions;

	public ConditionalAssertion(Predicate aPredicate,
			List<Assertion> ifAssertions, 
			List<Assertion> elseAssertions) {
		this.predicate = aPredicate;
		this.ifAssertions = ifAssertions;
		this.elseAssertions = elseAssertions;
	}

	@Override
	public void assertTrueIn(Context aContext) {
		if (predicate.evaluateIn(aContext)) {
			runIn(this.ifAssertions, aContext);
		} else {
			runIn(this.elseAssertions, aContext);
		}
	}

	private void runIn(List<Assertion> assertions, Context aContext) {
		for (Assertion anAssertion : assertions) {
			anAssertion.assertTrueIn(aContext);
		}
	}

}
