package expression;

import expression.exceptions.ExceptionsChecker;

public class Divide extends Binary {

    public Divide(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected String symbol() {
        return "/";
    }

    @Override
    protected int apply(int left, int right) {
        return ExceptionsChecker.divide(left, right);
    }

    @Override
    protected double apply(double left, double right) {
        return left / right;
    }

}
