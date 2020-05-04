import java.util.ArrayDeque;

public class ExcutableSequenceCommand extends Command {

    private ArrayDeque<Command> commands;

    public ExcutableSequenceCommand(ArrayDeque<Command> commands) {
        this.commands = commands;
    }

    public ArrayDeque<Command> getCommands() {
        return commands;
    }

    public boolean isExcutableSequenceCommand() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        for (Command command : commands) {
            sb.append(command.toString());
            sb.append(" ");
        }
        sb.append(")");
        return sb.toString();
    }

}
