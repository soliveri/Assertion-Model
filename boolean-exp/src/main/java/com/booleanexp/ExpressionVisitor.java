package com.booleanexp;

public interface ExpressionVisitor {

	Object processAnd(AndExp expression);

	Object processConstant(Expression expression);

	Object processEqualTo(EqualTo expression);

	Object processGreaterOrEqualTo(GreaterOrEqualTo expression);

	Object processGreaterThan(GreaterThan expression);

	Object processLessOrEqualTo(LessOrEqualTo expression);

	Object processLessThan(LessThan expression);

	Object processNot(NotExp expression);

	Object processOr(OrExp expression);

	Object processVariable(VariableExp expression);

	Object visit(Expression expression);

	Object processIncludes(IncludesExp expression);

	Object processBeginsWith(BeginsWithExp expression);

	Object processEndsWith(EndsWithExp expression);

	Object processNullExp(NullExp nullExp);

	Object processIn(InExp inExp);
	
}
