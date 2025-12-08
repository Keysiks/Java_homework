package expression;

public class Variable extends Ordinary {
    public Variable(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Variable && ((Variable) o).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if ("x".equals(name)) return x;
        if ("y".equals(name)) return y;
        return z;
    }

    @Override
    public double evaluateD(double x, double y, double z) {
        if ("x".equals(name)) return x;
        if ("y".equals(name)) return y;
        return z;
    }
}
