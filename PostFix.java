import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class PostFix {

    private Lexer lexer;
    Stack<Command> stack = new Stack<Command>();
    Queue<Command> commandsQueue = new ArrayDeque<Command>();

    public PostFix(Lexer lexer) {
        this.lexer = lexer;
    }

    private ArrayDeque<Command> getExcutableSequenceCommands() throws Exception {
        ArrayDeque<Command> excutableSequenceCommands = new ArrayDeque<Command>();
        for (Token token = lexer.read(); !token.isRightParen(); token = lexer.read()) {
            if (token.isNumber()) {
                excutableSequenceCommands.add(new NumCommand(Integer.parseInt(token.text)));
            } else if (token.isString()) {
                excutableSequenceCommands.add(new StrCommand(token.text));
            } else if (token.isLeftParen()) {
                excutableSequenceCommands.add(new ExcutableSequenceCommand(getExcutableSequenceCommands()));
            } else {
                throw new Exception("ParsingError");
            }
        }
        return excutableSequenceCommands;
    }

    public int exec() throws Exception {

        // 先頭がカッコでなければエラー
        if (!lexer.read().isLeftParen())
            throw new Exception("This is not postfix program");

        // 二番目が"postfix"でなければエラー
        if (!lexer.read().text.equals("postfix"))
            throw new Exception("This is not postfix program");

        // 引数の数を読み捨て
        int argc = Integer.parseInt(lexer.read().toString());

        // トークンを取り出してCommandオブジェクトのリストを作成
        for (Token token = lexer.read(); !token.Null(); token = lexer.read()) {
            if (token.isNumber()) {
                commandsQueue.add(new NumCommand(Integer.parseInt(token.text)));
            } else if (token.isString()) {
                commandsQueue.add(new StrCommand(token.text));
            } else if (token.isLeftParen()) {
                commandsQueue.add(new ExcutableSequenceCommand(getExcutableSequenceCommands()));
            } else {
            }
        }

        // printStackAndQue();
        // 実行

        for (Command command = commandsQueue.poll(); command != null; command = commandsQueue.poll()) {

            if (command.isExcutableSequenceCommand()) {
                stack.push(command);
            } else if (command.isNumCommand()) {
                stack.push(command);
            } else if (command.isStrCommand()) {
                String instruction = command.toString();
                if (instruction.equals("swap")) {
                    Command first = stack.pop();
                    Command second = stack.pop();
                    stack.push(first);
                    stack.push(second);
                } else if (instruction.equals("add")) {
                    var first = (NumCommand) (stack.pop());
                    var second = (NumCommand) (stack.pop());
                    stack.push(first.add(second));
                } else if (instruction.equals("mul")) {
                    var first = (NumCommand) (stack.pop());
                    var second = (NumCommand) (stack.pop());
                    stack.push(first.mul(second));
                } else if (instruction.equals("sub")) {
                    var first = (NumCommand) (stack.pop());
                    var second = (NumCommand) (stack.pop());
                    stack.push(second.sub(first));
                } else if (instruction.equals("exec")) {
                    ExcutableSequenceCommand excutableSequenceCommand = (ExcutableSequenceCommand) stack.pop();
                    for (Command subcommand : excutableSequenceCommand.getCommands())
                        commandsQueue.add(subcommand);
                } else if (instruction.equals("sel")) {
                    Command first = stack.pop();
                    Command second = stack.pop();
                    int flag = ((NumCommand) (stack.pop())).eval();

                    // System.out.println("test" + flag);

                    if (flag == 0) {
                        stack.push(first);
                    } else {
                        stack.push(second);
                    }

                } else if (instruction.equals("lt")) {
                    int first = ((NumCommand) (stack.pop())).eval();
                    int second = ((NumCommand) (stack.pop())).eval();

                    if (second < first) {
                        stack.push(new NumCommand(1));
                    } else {
                        stack.push(new NumCommand(0));
                    }
                } else {
                    throw new Exception("unknown instruction: " + instruction);
                }
            } else {
                throw new Exception("unknown exception");
            }

            // printStackAndQue();
        }

        Command command = stack.pop();
        if (command.isExcutableSequenceCommand() || command.isStrCommand())
            throw new Exception();

        return ((NumCommand) command).eval();
    }

    private void printStackAndQue() {
        System.out.println("======");
        System.out.println("Stack");
        printCommandStack();
        System.out.println("Que");
        printInputQue();
    }

    private void printInputQue() {
        for (Command _command : commandsQueue)
            System.out.println(_command.toString());
    }

    private void printCommandStack() {
        for (Command _command : stack)
            System.out.println(_command.toString());
    }
}
