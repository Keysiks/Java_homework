package expression.exceptions;


public class ExceptionsChecker {
    private static final int minInt = Integer.MIN_VALUE;
    private static final int maxInt = Integer.MAX_VALUE;

    private ExceptionsChecker() {}

    public static int add(int a, int b) {
        if (a > 0 && b > 0 && a > maxInt - b) {
            throw new OverflowException("OverflowException");
        }
        if (a < 0 && b < 0 && a < minInt - b) {
            throw new OverflowException("OverflowException");
        }
        return a + b;
    }

    public  static int subtract(int a, int b) {
        if (b > 0 && a < minInt + b) {
            throw new OverflowException("OverflowException");
        }
        if (b < 0 && a > maxInt + b) {
            throw new OverflowException("OverflowException");
        }
        return a - b;
    }

    public  static int multiply(int a, int b) {
        if (a == 0 || b == 0){
            return 0;
        }
        if ((a > 0) & (b > 0 && a > maxInt / b ||
                    b < 0 && b < minInt / a)) {
                throw new OverflowException("OverflowException");

        }
        if (b > 0 && a < minInt / b ||
                b < 0 && a < maxInt / b) {
            throw new OverflowException("OverflowException");
        }
        return  a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException("DivisionByZeroException");
        }
        if (a == minInt && b == -1) {
            throw new OverflowException("OverflowException");
        }
        return a / b;
    }

    public static int negate(int a) {
        if (a == minInt) {
            throw new OverflowException("OverflowException");
        }
        return -a;
    }
}

