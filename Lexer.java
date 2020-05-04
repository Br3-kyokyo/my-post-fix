
public class Lexer {
    private String program;
    private int index = 0;

    public Lexer(String program) {
        this.program = program;
    }

    public Token read() {
        StringBuilder sb = new StringBuilder();
        char ch;

        do {
            // indexが文字列の長さを超えたとき
            if (index == program.length())
                return new NullToken("EOL");
        } while (isSpace(ch = getChar()));

        // chは空白以外が入っている
        if (isParen(ch)) {
            sb.append(ch);
            if (isLeftParen(ch)) {
                return new LeftParenToken(sb.toString());
            } else {
                return new RightParenToken(sb.toString());
            }
        } else if (isNumber(ch)) {
            do {
                sb.append(ch);
                ch = getChar();
            } while (!isSpace(ch) && !isParen(ch));
            ungetChar();
            return new NumToken(sb.toString());
        } else {
            do {
                sb.append(ch);
                ch = getChar();
            } while (!isSpace(ch) && !isParen(ch));
            ungetChar();
            return new StrToken(sb.toString());
        }

    }

    private boolean isLeftParen(char ch) {
        return ch == '(';
    }

    private void ungetChar() {
        index--;
    }

    private boolean isSpace(char c) {
        return c == ' ';
    }

    private char getChar() {
        return program.charAt(index++);
    }

    private boolean isNumber(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isParen(char ch) {
        return ch == '(' || ch == ')';
    }
}