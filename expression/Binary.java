// java
package expression;

import expression.exceptions.OverflowException;

public abstract class Binary implements MyExpression {
    protected final MyExpression left;
    protected final  MyExpression right;

    protected abstract String symbol();

    public Binary(MyExpression left, MyExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        result = 31 * result + getClass().hashCode();
        return result;
    }

    public String toString() {
        return "(" + this.left.toString() + " " + this.symbol() + " " + this.right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Binary binary = (Binary) o;
        return left.equals(binary.left) && right.equals(binary.right);
    }

    public int evaluate(int x) {
        return apply(this.left.evaluate(x), this.right.evaluate(x));
    }

    // сюда добавить apply для дробных и 3х переменных
    public int evaluate(int x, int y, int z) {
        return apply(this.left.evaluate(x, y, z), this.right.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        return apply(this.left.evaluateD(x, y, z), this.right.evaluateD(x, y, z));
    }

    protected abstract int apply(int left, int right);

    protected abstract double apply(double left, double right);

    protected int evklids_alg(int a, int b) {
        if ((a == Integer.MIN_VALUE && b == 0) ||
                (b == Integer.MIN_VALUE && a == 0) ||
                (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE)) {
            throw new OverflowException("OverflowException");
        }
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        if (a < 0) {
            a = -a;
        }
        return a;
    }

}
