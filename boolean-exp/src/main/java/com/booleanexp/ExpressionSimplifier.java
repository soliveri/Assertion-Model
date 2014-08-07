package com.booleanexp;

import java.util.Collection;

public class ExpressionSimplifier implements ExpressionVisitor {

	private Context context;

	public Object processAnd(AndExp expression) {
		return ((Expression) visit(expression.getLeft()))
				.and((Expression) visit(expression.getRight()));
	}
	
	public Object processOr(OrExp expression) {
		return ((Expression) visit(expression.getLeft()))
				.or((Expression) visit(expression.getRight()));
	}
	
	public Object processNot(NotExp expression) {
		return ((Expression) visit(expression.internalExp())).not();
	}

	public Object processConstant(Expression expression) {
		return expression;
	}
	
	public Object processVariable(final VariableExp expression) {
		String objectReference = (String) expression.reference;
		ConstantExp isPropertyIndexed = ConstantExp.forValue(objectReference.contains("."));
		return ConstantExp.forValue(
				isPropertyIndexed.ifTrueElse(
					IndexedValueRetrievalClosure.toRetrieveOf(objectReference, context), 
					NOPClosure.forValue(context.valueFor(expression.reference))));
	}
	
	public interface Comparator {
		boolean compareTo(Object anObject, Object anotherObject);
	}
	
	private Object compareWithOtherwiseEvaluate(BinaryExp aBinaryExpression, final Comparator aComparation, Closure aClosure) {
		final Expression simpleLeft = (Expression) visit(aBinaryExpression.getLeft());
		final Expression simpleRight = (Expression) visit(aBinaryExpression.getRight());
		Closure comparationClosure = new Closure() {
			@Override
			public Object evaluate() {
				Object leftValue = ((ConstantExp) simpleLeft).value;
				Object rightValue = ((ConstantExp) simpleRight).value;
				return ConstantExp.forValue(aComparation.compareTo(prepared(leftValue), prepared(rightValue)));
			}
			private Object prepared(Object anObject) {
				boolean isString = String.class.isAssignableFrom(anObject.getClass());
				return ConstantExp.forValue(isString).ifTrueElse(StringTrimmerClosure.forValue(anObject), NOPClosure.forValue(anObject));
			}
		};
		Expression isConstant = simpleLeft.isConstant().and(simpleRight.isConstant());
		return isConstant.ifTrueElse(comparationClosure, aClosure);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processEqualTo(final EqualTo expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				if (Comparable.class.isAssignableFrom(anObject.getClass())) {
					return ((Comparable) anObject).compareTo((Comparable) anotherObject) == 0;	
				}
				return anObject.equals(anotherObject);
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isEqualTo(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processGreaterOrEqualTo(final GreaterOrEqualTo expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((Comparable) anObject).compareTo((Comparable) anotherObject) >= 0;
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isGreaterOrEqualTo(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processGreaterThan(final GreaterThan expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((Comparable) anObject).compareTo((Comparable) anotherObject) > 0;
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isGreaterThan(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processLessOrEqualTo(final LessOrEqualTo expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((Comparable) anObject).compareTo((Comparable) anotherObject) <= 0;
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isLessOrEqualTo(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object processLessThan(final LessThan expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((Comparable) anObject).compareTo((Comparable) anotherObject) < 0;
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isLessThan(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}
	
	@Override
	public Object processIn(final InExp expression) {
		Comparator comparator = new Comparator() {
			@SuppressWarnings("rawtypes")
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((Collection) anotherObject).contains(anObject);
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).isIn(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	public Object processIncludes(final IncludesExp expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((String) anObject).indexOf((String) anotherObject) != -1;
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).includes(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	public Object processBeginsWith(final BeginsWithExp expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((String) anObject).startsWith((String) anotherObject);
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).beginsWith(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}

	public Object processEndsWith(final EndsWithExp expression) {
		Comparator comparator = new Comparator() {
			@Override
			public boolean compareTo(Object anObject, Object anotherObject) {
				return ((String) anObject).endsWith((String) anotherObject);
			}
		};
		Closure anotherClosure = new Closure() {
			@Override
			public Object evaluate() {
				return visit(expression.getLeft()).endsWith(visit(expression.getRight()));
			}
		};
		return compareWithOtherwiseEvaluate(expression, comparator, anotherClosure);
	}
	
	@Override
	public Object processNullExp(NullExp nullExp) {
		ConstantExp aConstantExp = (ConstantExp) visit(nullExp.getValue());
		return aConstantExp.isNull();
	}	

	public Object simplifyIn(Expression expression, Context aContext) {
		context = aContext;
		return visit(expression);
	}

	public Expression visit(Expression expression) {
		return (Expression) expression.visitedBy(this);
	}
	
}
