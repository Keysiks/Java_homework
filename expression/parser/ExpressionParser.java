package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    @Override
    public MyExpression parse(String expression) {
        Parser parser = new Parser(new StringSource(expression));
        MyExpression result = parser.parseExpression();
        return result;
    }

    private static class Parser extends BaseParser {
        Parser(CharSource source) {
            super(source);
            skipWhitespace();
        }

        MyExpression parseExpression() {
            MyExpression result = parseOr();
            skipWhitespace();
            if (!checkEOF()) {
                throw source.error("Unexpected character: " + current());
            }
            return result;
        }

        private MyExpression parseOr() {
            MyExpression left = parseXor();
            while (true) {
                skipWhitespace();
                if (take('|')) {
                    left = new Or(left, parseXor());
                } else {
                    return left;
                }
            }
        }

        private MyExpression parseXor() {
            MyExpression left = parseAnd();
            while (true) {
                skipWhitespace();
                if (take('^')) {
                    left = new Xor(left, parseAnd());
                } else {
                    return left;
                }
            }
        }

        private MyExpression parseAnd() {
            MyExpression left = parseAddSub();
            while (true) {
                skipWhitespace();
                if (take('&')) {
                    left = new And(left, parseAddSub());
                } else {
                    return left;
                }
            }
        }

        private MyExpression parseAddSub() {
            MyExpression left = parseMulDiv();
            while (true) {
                skipWhitespace();
                if (take('+')) {
                    left = new Add(left, parseMulDiv());
                } else if (take('-')) {
                    left = new Subtract(left, parseMulDiv());
                } else {
                    return left;
                }
            }
        }

        private MyExpression parseMulDiv() {
            MyExpression left = parseUnary();
            while (true) {
                skipWhitespace();
                if (take('*')) {
                    left = new Multiply(left, parseUnary());
                } else if (take('/')) {
                    left = new Divide(left, parseUnary());
                } else {
                    return left;
                }
            }
        }

        private MyExpression parseUnary() {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return parseNumber(true);
                }
                skipWhitespace();
                return new Negate(parseUnary());
            }
            if (take('(')) {
                MyExpression inside = parseOr();
                skipWhitespace();
                expect(')');
                return inside;
            }
            if (between('0', '9')) {
                return parseNumber(false);
            }
            if (Character.isLetter(current())) {
                return parseVariable();
            }
            throw source.error("Unexpected symbol: " + current());
        }

        private MyExpression parseVariable() {
            StringBuilder sb = new StringBuilder();
            sb.append(take());
            while (Character.isLetterOrDigit(current())) {
                sb.append(take());
            }
            return new Variable(sb.toString());
        }

        private MyExpression parseNumber(boolean negative) {
            StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }
            while (between('0', '9')) {
                sb.append(take());
            }
            if (sb.length() == (negative ? 1 : 0)) {
                throw source.error("Number expected");
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw source.error("Invalid integer: " + sb);
            }
        }
    }
}
