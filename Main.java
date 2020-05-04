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

            try {
                int result = postfix.exec();
                System.out.println("=> " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            linenum++;
            System.out.print("postfix:" + linenum + ">");
        }
        scan.close();
    }
}
