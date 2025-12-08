package expression;

public abstract class Ordinary implements MyExpression {
    public int constant;
    public String name = "";
    public double constantDouble;

    public Ordinary(int x) {
        this.constant = x;
        this.name = "";
    }

    public Ordinary(double x) {
        this.constantDouble = x;
    }

    public Ordinary(String name) {
        this.name = name;
        this.constant = 0;
    }

    public int evaluate(int x) {
        return name.isEmpty() ? constant : x;
    }

    public String toString() {
        return name.isEmpty() ? Integer.toString(constant) : name;
    }

    public abstract boolean equals(Object o);

    public int evaluate(int x, int y, int z) {
        return name.isEmpty() ? constant : x;
    }

    public double evaluateD(double x, double y, double z) {
        if (!name.isEmpty()) {
            return x;
        }
        return Double.compare(constantDouble, 0.0) != 0 ? constantDouble : (double) constant;
    }

}
