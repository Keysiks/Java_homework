package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;


public class ExceptionsChecker {
    private static final int minInt = Integer.MIN_VALUE;
    private static final int maxInt = Integer.MAX_VALUE;

    private ExceptionsChecker() {}

    public static int add(int a, int b) {
        if (b > 0 && a > maxInt - b) {
            throw new OverflowException("OverflowException");
        }
        if (b < 0 && a < minInt - b) {
            throw new OverflowException("OverflowException");
        }
        return a + b;
    }

    public  static int subtract(int a, int b) {
        if (b < 0 && a < minInt + b) {
            throw new OverflowException("OverflowException");
        }
        if (b > 0 && a > maxInt - b) {
            throw new OverflowException("OverflowException");
        }
        return a - b;
    }

    public  static int multiply(int a, int b) {
        if ((a > 0) & (b > 0 && a > Integer.MAX_VALUE / b ||
                    b < 0 && b < Integer.MIN_VALUE / a)) {
                throw new OverflowException("OverflowException");

        }
        if (b > 0 && a < Integer.MIN_VALUE / b ||
                b < 0 && a < Integer.MAX_VALUE / b) {
            throw new OverflowException("OverflowException");
        }
        return  a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException("DivisionByZeroException");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("OverflowException");
        }
        return a / b;
    }

    public static int negate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("OverflowException");
        }
        return -a;
    }
}
