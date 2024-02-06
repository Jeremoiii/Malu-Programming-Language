import classes.Ast;
import classes.Lexer;
import classes.Parser;
import classes.utils.ObjectPrinter;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        testLexer();
        repl();
    }

    public static void repl() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        System.out.print("Repl v0.1");

        while (true) {
            System.out.println("\n> ");
            String input = scanner.nextLine();
            if (input.isEmpty() || input.contains("exit")) {
                System.exit(0);
            }

            Ast program = parser.produceAST(input);

            System.out.println(ObjectPrinter.deserializeObjectToString(program));
        }
    }

    public static void testLexer() {
        Lexer lexer = new Lexer();
        String sourceCode = "(let x = 42)";
        List<Lexer.Token> tokens = lexer.tokenize(sourceCode);

        for (Lexer.Token token : tokens) {
            System.out.println("Token: " + token.getValue() + ", Type: " + token.getType());
        }
    }
}