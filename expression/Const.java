package expression;

public class Const extends Ordinary {
    public Const(int constant) {
        super(constant);
    }

    public Const(double constant) {
        super(constant);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Const other)) return false;
        if (!this.name.isEmpty() || !other.name.isEmpty()) {
            return this.name.equals(other.name);
        }
        if (Double.compare(this.constantDouble, 0.0) != 0 || Double.compare(other.constantDouble, 0.0) != 0) {
            return Double.compare(this.constantDouble, other.constantDouble) == 0;
        }
        return this.constant == other.constant;
    }

    @Override
    public int hashCode() {
        if (Double.compare(constantDouble, 0.0) != 0) {
            long bits = Double.doubleToLongBits(constantDouble);
            return (int) (bits ^ (bits >>> 32));
        }
        return this.constant;
    }

}
