package expression.parser;


public class BaseParser {
    protected CharSource source;
    private char ch;
    public static final char END = (char) -1;


    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected String getNeighborhood() {
        return source.getNeighborhood();
    }

    protected char current() {
        return ch;
    }

    protected boolean test(char c) {
        return ch == c;
    }

    protected char take() {
        char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }

    protected boolean take(char c) {
        if (test(c)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean checkEOF() {
        return ch == END;
    }

    protected void expect(char c) {
        if (!take(c)) {
            throw source.error("Expected: " + c + " , but found: " + ch);
        }
    }

    protected boolean between(char start, char end) {
        return start <= ch && ch <= end;
    }

    protected void skipWhitespace() {
        while (!checkEOF() && Character.isWhitespace(ch)) {
            take();
        }
    }

    protected boolean takeWord(String word) {
        if (current() != word.charAt(0)) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (!test(word.charAt(i))) {
                throw source.error("Unexpected symbol: " + current());
            }
            take();
        }
        return true;
    }

}

