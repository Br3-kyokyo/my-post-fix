import java.util.Scanner;

public class LexerRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // １行読み込んだ内容を出力する
            // System.out.println(scan.nextLine());
            Lexer lexer = new Lexer(scan.nextLine());

            for (Token token = lexer.read(); !token.EOL(); token = lexer.read()) {
                System.out.println(token.text);
            }
        }
        scan.close();
    }
}