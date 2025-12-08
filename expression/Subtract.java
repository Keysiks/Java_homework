package expression;

public class Subtract extends Binary {

    public Subtract(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected String symbol() {
        return "-";
    }

    @Override
    protected int apply(int left, int right) {
        return left - right;
    }

    @Override
    protected double apply(double left, double right) {
        return left - right;
    }

}
