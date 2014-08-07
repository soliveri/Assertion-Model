package com.assertion;

import java.util.Collection;
import java.util.Map;

import org.joda.time.LocalDateTime;

import com.booleanexp.ConstantExp;
import com.booleanexp.Context;
import com.booleanexp.Expression;
import com.booleanexp.FalseExp;
import com.booleanexp.NotExp;
import com.booleanexp.NullExp;
import com.booleanexp.VariableExp;

public class ExpressionAssertion implements Assertion {

	private Expression expression;
	private String failureMessage;

	public ExpressionAssertion(Expression anExpression, String aFailureMessage) {
		this.expression = anExpression;
		this.failureMessage = aFailureMessage;
	}

	public void assertTrue() {
		this.assertTrueIn(new Context());
	}
	
	public void assertTrueIn(Context aContext) {
		if (expression.simplifiedIn(aContext).isFalse()) {
			throw new RuntimeException(failureMessage);
		}
	}

	public static ExpressionAssertion forDescribedBy(Expression anExpression, String aFailureMessage) {
		return new ExpressionAssertion(anExpression, aFailureMessage);
	}

	public static ExpressionAssertion notEmptyStringAsVariableDescribedBy(String anObjectIdentifier, String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				new NotExp(NullExp.forValue(new VariableExp(anObjectIdentifier))).and(new NotExp(new VariableExp(anObjectIdentifier).isEqualTo(ConstantExp.forValue("")))), 
				anErrorMessage);
	}
	
	public static ExpressionAssertion notEmptyStringAsConstantDescribedBy(Object anObject, String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				new NotExp(NullExp.forValue(ConstantExp.forValue(anObject))).and(new NotExp(ConstantExp.forValue(anObject).isEqualTo(ConstantExp.forValue("")))), 
				anErrorMessage);
	}

	@SuppressWarnings("rawtypes")
	public static ExpressionAssertion isInDescribedBy(Object anObject, Collection aCollection, String anErrorMsg) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(anObject).isIn(ConstantExp.forValue(aCollection)), 
				anErrorMsg);
	}

	public static ExpressionAssertion notNullDescribedBy(Double anObject, String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				new NotExp(NullExp.forValue(ConstantExp.forValue(anObject))), 
				anErrorMessage);
	}

	public static ExpressionAssertion valueBetweenDescribedBy(Double aValue, Double from, Double to, String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(aValue).isGreaterOrEqualTo(ConstantExp.forValue(from)).and(
						ConstantExp.forValue(aValue).isLessOrEqualTo(ConstantExp.forValue(to))), 
				anErrorMessage);
	}

	public static ExpressionAssertion isGreatherThanOrEqualToDescribedBy(LocalDateTime aDate, LocalDateTime anotherDate, String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(aDate.toDate()).isGreaterOrEqualTo(ConstantExp.forValue(anotherDate.toDate())), 
				anErrorMessage);
	}
	
	public static ExpressionAssertion isGreatherThanOrEqualToDescribedBy(String anObjectIdentifier,
			String anotherObjectIdentifier, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				new VariableExp(anObjectIdentifier).isGreaterOrEqualTo(new VariableExp(anotherObjectIdentifier)), 
				aFailureMessage);
	}
	
	public static ExpressionAssertion isGreatherThanDescribedBy(String anObjectIdentifier,
			String anotherObjectIdentifier, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				new VariableExp(anObjectIdentifier).isGreaterThan(new VariableExp(anotherObjectIdentifier)), 
				aFailureMessage);
	}
	
	public static ExpressionAssertion isGreatherThanDescribedBy(Object anObject,
			Object anotherObject, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(anObject).isGreaterThan(ConstantExp.forValue(anotherObject)), 
				aFailureMessage);
	}
	
	public static ExpressionAssertion isGreatherThanDescribedBy(LocalDateTime aLocalDateTime,
			LocalDateTime anotherLocalDateTime, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(aLocalDateTime.toDate()).isGreaterThan(ConstantExp.forValue(anotherLocalDateTime.toDate())), 
				aFailureMessage);
	}
	
	public static ExpressionAssertion isBeforeThanDescribedBy(String anObjectIdentifier,
			String anotherObjectIdentifier, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				new VariableExp(anObjectIdentifier).isLessThan(new VariableExp(anotherObjectIdentifier)), 
				aFailureMessage);
	}

	public static ExpressionAssertion notEmptyDescribedBy(
			Collection<?> aCollection,
			String anErrorMessage) {
		return ExpressionAssertion.forDescribedBy(
				ConstantExp.forValue(aCollection.size()).isGreaterThan(ConstantExp.forValue(0)), 
				anErrorMessage);
	}

	public static ExpressionAssertion failedDescribedBy(String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(new FalseExp(), aFailureMessage);
	}

	public static ExpressionAssertion assertNotExistsDescribedBy(
			Object anObject,
			String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(
				NullExp.forValue(ConstantExp.forValue(anObject)), 
				aFailureMessage);
	}

	@SuppressWarnings("rawtypes")
	public static ExpressionAssertion isInDescribedBy(String anObject, Map aMap, String aFailureMessage) {
		return isInDescribedBy(anObject, aMap.keySet(), aFailureMessage);
	}

	public static ExpressionAssertion isIncludedInDescribedBy(String aString, String anotherString, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(anotherString).includes(ConstantExp.forValue(aString)), aFailureMessage);
	}

	public static ExpressionAssertion startsWithDescribedBy(String aString, String aSubstring, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(aString).beginsWith(ConstantExp.forValue(aSubstring)), aFailureMessage);
	}

	public static ExpressionAssertion endsWithDescribedBy(String aString, String aSubstring, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(aString).endsWith(ConstantExp.forValue(aSubstring)), aFailureMessage);
	}

	public static ExpressionAssertion isNotIncludedInDescribedBy(String aString, String anotherString, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(anotherString).includes(ConstantExp.forValue(aString)).not(), aFailureMessage);
	}

	public static ExpressionAssertion notStartsWithDescribedBy(String aString, String aSubstring, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(aString).beginsWith(ConstantExp.forValue(aSubstring)).not(), aFailureMessage);
	}

	public static ExpressionAssertion notEndsWithDescribedBy(String aString, String aSubstring, String aFailureMessage) {
		return ExpressionAssertion.forDescribedBy(ConstantExp.forValue(aString).endsWith(ConstantExp.forValue(aSubstring)).not(), aFailureMessage);
	}

	@SuppressWarnings("rawtypes")
	public static Assertion isEveryCharOfContainedIn(final String aString, final Collection aCollectionOfChars, final String aFailureMessage) {
		return new Assertion() {
			@Override
			public void assertTrueIn(Context aContext) {
				for (int index = 0; index < aString.length(); index++) {
					ExpressionAssertion.isInDescribedBy(aString.charAt(index), aCollectionOfChars, aFailureMessage).assertTrue();
				}
			}
		};
	}

	public static ExpressionAssertion isEqualToDescribedBy(Object anObject, Object anotherObject, String aFailureMessage) {
		return forDescribedBy(ConstantExp.forValue(anObject).isEqualTo(ConstantExp.forValue(anotherObject)), aFailureMessage);
	}

}
