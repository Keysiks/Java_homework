package expression.exceptions;

import expression.ExceptionsChecker;
import expression.MyExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected int apply(int left, int right) {
        return ExceptionsChecker.subtract(left, right);
    }
}
