import java.util.ArrayDeque;

public class NumCommand extends Command {

    private int value;

    public NumCommand(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(this.value);
    }

    public int eval() {
        return value;
    }

    public boolean isNumCommand() {
        return true;
    }

    public NumCommand add(NumCommand second_term) {
        return new NumCommand(this.eval() + second_term.eval());
    }

    public NumCommand mul(NumCommand second_term) {
        return new NumCommand(this.eval() * second_term.eval());
    }

    public NumCommand sub(NumCommand second_term) {
        return new NumCommand(this.eval() - second_term.eval());
    }

    public NumCommand div(NumCommand second_term) {
        return new NumCommand(this.eval() / second_term.eval());
    }

    public NumCommand rem(NumCommand second_term) {
        return new NumCommand(this.eval() % second_term.eval());
    }
}