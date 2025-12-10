package expression.parser;

public class StringSource implements CharSource {
    private final String string;
    private int pos;
    private static final int EPSILON = 5;

    public StringSource(String string) {
        this.string = string;
    }

    @Override
    public String getNeighborhood() {
        int left = Integer.max(pos - EPSILON, 0);
        int right = Integer.min(pos + EPSILON, string.length());
        return string.substring(left, right);
    }

    @Override
    public boolean hasNext() {
        return pos < string.length();
    }

    @Override
    public char next() {
        return string.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(pos + ":" + message);
    }
}