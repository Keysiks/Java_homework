package expression;

public class GCD extends Binary{
    public GCD(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected String symbol() {
        return "gcd";
    }

    @Override
    protected int apply(int left, int right) {
        return evklids_alg(left, right);
    }

    @Override
    protected double apply(double left, double right) {
        return 0;
    }

}
