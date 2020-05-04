import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class PostFix {

    Stack<Command> stack = new Stack<Command>();
    Queue<Command> commandsQueue = new ArrayDeque<Command>();

    public int exec(Lexer lexer) throws Exception {

        // 先頭がカッコでなければエラー
        if (!lexer.read().isLeftParen())
            throw new Exception("This is not postfix program");

        // 二番目が"postfix"でなければエラー
        if (!lexer.read().text.equals("postfix"))
            throw new Exception("This is not postfix program");

        // 引数の数を読み捨て
        int argc = Integer.parseInt(lexer.read().toString());

        // トークンを取り出してリストを作成
        for (Token token = lexer.read(); !token.Null(); token = lexer.read()) {
            ArrayDeque<Token> queue = new ArrayDeque<Token>();
            if (token.isNumber() || token.isString()) {
                queue.add(token);
                commandsQueue.add(new Command(queue, false));
            } else if (token.isLeftParen()) {
                System.out.println(token.text);
                int depth = 0;
                for (token = lexer.read(); !token.isRightParen() || depth != 0; token = lexer.read()) {
                    if (token.isLeftParen())
                        depth++;
                    if (token.isRightParen())
                        depth--;
                    queue.add(token);
                }
                commandsQueue.add(new Command(queue, true));
            } else {

            }
        }

        // printNow();

        // 実行

        for (Command command = commandsQueue.poll(); command != null; command = commandsQueue.poll()) {

            if (command.isExcutable()) {
                stack.push(command);
            } else if (command.getTokens().getFirst().isNumber()) {
                stack.push(command);
            } else {
                String instruction = command.getTokens().getFirst().text;
                if (instruction.equals("swap")) {
                    Command first = stack.pop();
                    Command second = stack.pop();
                    stack.push(first);
                    stack.push(second);
                } else if (instruction.equals("add")) {
                    int first = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    int second = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    ArrayDeque<Token> queue = new ArrayDeque<Token>();
                    queue.add(new NumToken(first + second));
                    stack.push(new Command(queue, false));
                } else if (instruction.equals("mul")) {
                    int first = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    int second = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    ArrayDeque<Token> queue = new ArrayDeque<Token>();
                    queue.add(new NumToken(first * second));
                    stack.push(new Command(queue, false));

                } else if (instruction.equals("sub")) {
                    int first = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    int second = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    ArrayDeque<Token> queue = new ArrayDeque<Token>();
                    queue.add(new NumToken(second - first));
                    stack.push(new Command(queue, false));

                } else if (instruction.equals("exec")) {
                    for (Token token : stack.pop().getTokens()) {
                        ArrayDeque<Token> queue = new ArrayDeque<Token>();
                        boolean excutableCommandBlock = false;

                        if (excutableCommandBlock) {
                            if (token.isRightParen()) {
                                excutableCommandBlock = false;
                                commandsQueue.add(new Command(queue, true));
                            } else {
                                queue.add(token);
                            }
                        }

                        if (token.isNumber() || token.isString()) {
                            queue.add(token);
                            commandsQueue.add(new Command(queue, false));
                        } else {
                            excutableCommandBlock = true;
                        }
                    }

                } else if (instruction.equals("sel")) {

                    Command first = stack.pop();
                    Command second = stack.pop();
                    int flag = Integer.parseInt(stack.pop().getTokens().getFirst().text);

                    if (flag == 0) {
                        stack.push(first);
                    } else {
                        stack.push(second);
                    }
                } else if (instruction.equals("lt")) {
                    int first = Integer.parseInt(stack.pop().getTokens().getFirst().text);
                    int second = Integer.parseInt(stack.pop().getTokens().getFirst().text);

                    ArrayDeque<Token> queue = new ArrayDeque<Token>();
                    if (second < first) {
                        queue.add(new NumToken(1));
                    } else {
                        queue.add(new NumToken(0));

                    }
                    stack.push(new Command(queue, false));
                } else {
                    throw new Exception("unknown instruction: " + instruction);
                }
            }

            // printNow();
        }

        Command command = stack.pop();
        if (command.isExcutable())
            throw new Exception();

        Token token = command.getTokens().getFirst();
        if (token.isString())
            throw new Exception();

        return Integer.parseInt(token.text);
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
