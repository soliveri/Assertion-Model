package com.booleanexp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("serial")
public abstract class Expression implements Serializable {

	Expression[] arguments;
	@SuppressWarnings("rawtypes")
	Set referencesCache;

	protected void initialize() {
	}

	public Expression[] getArguments() {
		return arguments;
	}

	public void setArguments(Expression[] arguments) {
		invalidateCache();
		this.arguments = arguments;
	}

	private void invalidateCache() {
		referencesCache = null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set references() {
		Iterator iterator;
		if (referencesCache == null) {
			referencesCache = new HashSet();
			iterator = variables().iterator();
			while (iterator.hasNext()) {
				referencesCache.add(((VariableExp) iterator.next())
						.getReference());
			}
		}
		return referencesCache;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Set variables() {
		Set variablesCache;

		variablesCache = new HashSet();
		for (int i = 0; i < arguments.length; i++) {
			variablesCache.addAll(arguments[i].variables());
		}
		return variablesCache;
	}

	@SuppressWarnings("rawtypes")
	public void setReferencesCache(Set referencesCache) {
		this.referencesCache = referencesCache;
	}

	public Expression and(Expression expression) {
		return new AndExp(expression, this);
	}

	public Expression or(Expression expression) {
		return new OrExp(expression, this);
	}

	public Expression isLessThan(Expression expression) {
		return new LessThan(this, expression);
	}

	public Expression isLessOrEqualTo(Expression expression) {
		return new LessOrEqualTo(this, expression);
	}

	public Expression isEqualTo(Expression expression) {
		return new EqualTo(this, expression);
	}

	public Expression isGreaterThan(Expression expression) {
		return new GreaterThan(this, expression);
	}

	public Expression isGreaterOrEqualTo(Expression expression) {
		return new GreaterOrEqualTo(this, expression);
	}

	public Expression isIn(Expression expression) {
		return new InExp(this, expression);
	}
	
	public Expression not() {
		return new NotExp(this);
	}

	public Expression beginsWith(Expression expression) {
		return new BeginsWithExp(this, expression);
	}

	public Expression endsWith(Expression expression) {
		return new EndsWithExp(this, expression);
	}

	public Expression includes(Expression expression) {
		return new IncludesExp(this, expression);
	}

	public String toString() {
		return basicAsString();
	}

	protected String asStringUnderAnd() {
		return basicAsString();
	}

	protected String asStringUnderOr() {
		return basicAsString();
	}

	protected String asStringUnderNot() {
		return basicAsString();
	}

	protected String basicAsString() {
		return "";
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return null;
	}

	public ConstantExp isConstant() {
		return new FalseExp();
	}

	public boolean isFalse() {
		return false;
	}

	public boolean isMagnitude() {
		return false;
	}

	public boolean isTrue() {
		return false;
	}

	public boolean isVariable() {
		return false;
	}

	public ConstantExp simplifiedIn(Context context) {
		return (ConstantExp) ((new ExpressionSimplifier()).simplifyIn(this, context));
	}

	public boolean valueIn(Context context) {
		return simplifiedIn(context).isTrue();
	}

	public ConstantExp simplified() {
		return simplifiedIn(new Context());
	}
	
	public Object ifTrueElse(Closure aClosure, Closure anotherClosure) {
		return this;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Expression))
			return false;
		final Expression expression = (Expression) o;
		if (referencesCache != null ? !referencesCache
				.equals(expression.referencesCache)
				: expression.referencesCache != null)
			return false;
		return true;
	}

	public int hashCode() {
		return (referencesCache != null ? referencesCache.hashCode() : 0);
	}
	
}
