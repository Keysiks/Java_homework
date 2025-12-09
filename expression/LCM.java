package expression;

import expression.exceptions.ExceptionsChecker;

public class LCM extends Binary {
    public LCM(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected String symbol() {
        return "lcm";
    }

    @Override
    protected int apply(int left, int right) {
        if (left == 0 || right == 0) {
            return 0;
        }
        int g = evklids_alg(left, right);
        int divided = left / g;
        return ExceptionsChecker.multiply(divided, right);
    }

    @Override
    protected double apply(double left, double right) {
        return 0;
    }

}
