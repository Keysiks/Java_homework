package expression.exceptions;

import expression.Divide;
import expression.MyExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected int apply(int left, int right) {
        return ExceptionsChecker.divide(left, right);
    }
}
