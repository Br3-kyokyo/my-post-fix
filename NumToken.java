
public class NumToken extends Token {

    public NumToken(String text) {
        super(text);
        // TODO Auto-generated constructor stub
    }

    public NumToken(int text) {
        super(Integer.toString(text));
        // TODO Auto-generated constructor stub
    }

    public boolean isNumber() {
        return true;
    }

}