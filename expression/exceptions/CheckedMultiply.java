package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected int apply(int left, int right) {
        return  ExceptionsChecker.multiply(left, right);
    }
}
