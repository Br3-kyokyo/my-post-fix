
public abstract class Token {
    String text;

    public Token(String text) {
        this.text = text;
    }

    public boolean isString() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isLeftParen() {
        return false;
    }

    public boolean isRightParen() {
        return false;
    }

    public boolean Null() {
        return false;
    }

    public String toString() {
        return text;
    }
}