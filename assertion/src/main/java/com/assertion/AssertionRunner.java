package com.assertion;

import java.util.List;

import com.booleanexp.Context;

public class AssertionRunner {

	public static final AssertionRunner INSTANCE = new AssertionRunner();

	public void runIn(List<Assertion> assertions, Context aContext) {
		for (Assertion anAssertion : assertions) {
			anAssertion.assertTrueIn(aContext);
		}
	}

	public void run(List<Assertion> assertions) {
		runIn(assertions, new Context());
	}

}
