import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        PostFix postfix;
        Lexer lexer;
        Scanner scan = new Scanner(System.in);

        int linenum = 0;
        System.out.print("postfix:" + linenum + ">");
        while (scan.hasNextLine()) {
            lexer = new Lexer(scan.nextLine());
            postfix = new PostFix(lexer);

            int result = postfix.exec(lexer);
            System.out.println("=> " + result);

            linenum++;
            System.out.print("postfix:" + linenum + ">");
        }
        scan.close();
    }
}
