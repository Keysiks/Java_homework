package expression.exceptions;

import expression.ExceptionsChecker;
import expression.MyExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(MyExpression value) {
        super(value);
    }

    @Override
    public int evaluate(final int x) {
        return ExceptionsChecker.negate(super.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return ExceptionsChecker.negate(super.evaluate(x, y, z));
    }
}
