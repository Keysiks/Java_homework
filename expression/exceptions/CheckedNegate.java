package expression.exceptions;

import expression.MyExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(MyExpression value) {
        super(value);
    }

    @Override
    public int evaluate(int x) {
        return ExceptionsChecker.negate(-super.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return ExceptionsChecker.negate(-super.evaluate(x, y, z));
    }
}
