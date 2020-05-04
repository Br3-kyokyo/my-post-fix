
public abstract class Command {

    // public Command(ArrayDeque<Token> queue, boolean excutable) {
    // this.queue = queue;
    // this.excutable = excutable;
    // }

    public boolean isExcutableSequenceCommand() {
        return false;
    }

    public boolean isNumCommand() {
        return false;
    }

    public boolean isStrCommand() {
        return false;
    }

    public abstract String toString();

}
