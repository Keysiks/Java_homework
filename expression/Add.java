package expression;

import expression.exceptions.ExceptionsChecker;

public class Add extends Binary {
    public Add(MyExpression left, MyExpression right) {
        super(left, right);
        // тут сделать через super
    }


    @Override
    protected String symbol() {
        return "+";
    }

    @Override
    protected int apply(int left, int right) {
        return left + right;
    }

    @Override
    protected double apply(double left, double right) {
        return left + right;
    }
}
