package expression;

public class Main {
    public static void main(String[] args) {
        Expression expr = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Variable("x"), new Const(2))), new Const(1));
        System.out.println(expr.evaluate(4));
        System.out.println(expr);
        Expression expr1 = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Variable("x"), new Const(2))), new Const(1));
        Expression expr2 = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Const(2), new Variable("x"))), new Const(1));
        System.out.println(expr1.equals(expr2));
        System.out.println(expr.equals(expr1));
        Expression expr3 = new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        );
        System.out.println(expr3.evaluate(4));
        System.out.println(expr3);
    }
}
