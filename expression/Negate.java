package expression;


public class Negate implements MyExpression {
    private final MyExpression value;

    public Negate(MyExpression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return -value.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -((value).evaluate(x, y, z));
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return -(value).evaluateD(x, y, z);
    }

    @Override
    public String toString() {
        return "-" + "(" + value.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Negate negate = (Negate) o;
        return value.equals(negate.value);
    }

    @Override
    public int hashCode() {
        return 31 * value.hashCode();
    }
}
