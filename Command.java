import java.util.ArrayDeque;

public class Command {

    private ArrayDeque<Token> queue;
    private boolean excutable;
    private Instructions instrution = Instructions.none;

    public Command(ArrayDeque<Token> queue, boolean excutable) {
        this.queue = queue;
        this.excutable = excutable;
    }

    public ArrayDeque<Token> getTokens() {
        return queue;
    }

    public boolean isExcutable() {
        return excutable;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        for (Token token : queue) {
            sb.append(token.text);
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }
}