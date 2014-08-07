package com.booleanexp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class VariableExp extends Expression implements Serializable {

	Object reference;

	public VariableExp(Object aReference) {
		reference = aReference;
	}

	public Object getReference() {
		return reference;
	}

	public void setReference(Object reference) {
		this.reference = reference;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Set variables() {
		Set variables;

		variables = new HashSet();
		variables.add(this);
		return variables;
	}

	protected String basicAsString() {
		return reference.toString();
	}

	public boolean isVariable() {
		return true;
	}

	public Object visitedBy(ExpressionVisitor visitor) {
		return visitor.processVariable(this);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof VariableExp))
			return false;
		final VariableExp variableExp = (VariableExp) o;
		if (!reference.equals(variableExp.reference))
			return false;
		return true;
	}

	public int hashCode() {
		return reference.hashCode();
	}
	
}
