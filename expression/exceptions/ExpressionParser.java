package expression.exceptions;

import expression.*;
import expression.parser.*;
import expression.parser.CharSource;
import expression.parser.StringSource;

public class ExpressionParser implements TripleParser {
    @Override
    public MyExpression parse(String expression) {
        Parser parser = new Parser(new StringSource(expression));
        return parser.parseExpression();
    }

    private static class Parser extends BaseParser {
        Parser(CharSource source) {
            super(source);
            skipWhitespace();
        }

        MyExpression parseExpression() {
            MyExpression result = parseGcdLcm();
            skipWhitespace();
            if (!checkEOF()) {
                throw new CharacterException("Unexpected character: " + getNeighborhood());
            }
            return result;
        }

        private  MyExpression parseGcdLcm() {
            MyExpression left = parseOr();
            while (true) {
                skipWhitespace();
                if (takeWord("gcd")) {
                    if (Character.isDigit(current())) {
                        throw new CharacterException("Unexpected character: " + getNeighborhood());
                    }
                    left = new GCD(left, parseAddSub());
                } else if (takeWord("lcm")) {
                    if (Character.isDigit(current())) {
                        throw new CharacterException("Unexpected character: " + getNeighborhood());
                    }
                    left = new LCM(left, parseAddSub());
                } else {
                    return left;
                }
            }
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
                    left = new CheckedAdd(left, parseMulDiv());
                } else if (take('-')) {
                    left = new CheckedSubtract(left, parseMulDiv());
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
                    left = new CheckedMultiply(left, parseUnary());
                } else if (take('/')) {
                    left = new CheckedDivide(left, parseUnary());
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
                return new CheckedNegate(parseUnary());
            }
            if (take('(')) {
                MyExpression inside = parseGcdLcm();
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
            throw new SymbolException("Unexpected symbol: " + getNeighborhood());
        }

        private MyExpression parseVariable() {
            StringBuilder sb = new StringBuilder();
            sb.append(take());
            while (Character.isLetterOrDigit(current())) {
                sb.append(take());
            }
            String name = sb.toString();
            if (!name.equals("x") && !name.equals("y") && !name.equals("z")) {
                throw new VariableException("Unexpected variable: " + name);
            }
            return new Variable(name);
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
            if (Character.isLetter(current())) {
                throw new CharacterException("Unexpected character: " + getNeighborhood());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new InvalidIntegerException("Invalid integer: " + sb);
            }
        }
    }
}
