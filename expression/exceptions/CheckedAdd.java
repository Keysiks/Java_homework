package expression.exceptions;
import expression.*;

public class CheckedAdd extends Add{
    public CheckedAdd(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected int apply(int left, int right) {
        return ExceptionsChecker.add(left, right);
    }
}
