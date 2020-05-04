import java.util.ArrayDeque;

public class StrCommand extends Command {

    private String text;

    public StrCommand(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }

    public boolean isStrCommand() {
        return true;
    }
}
