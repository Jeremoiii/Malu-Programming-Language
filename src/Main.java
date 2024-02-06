import classes.Ast;
import classes.Interfaces.Program;
import classes.Interfaces.Statement;
import classes.Lexer;
import classes.Parser;
import classes.runtime.Interpreter;
import classes.utils.ObjectPrinter;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        testLexer();
        repl();
    }

    public static void repl() {
        Parser parser = new Parser();
        System.out.println("\nRepl v0.1");

        // Continue Repl Until User Stops Or Types `exit`
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            // Check for no user input or exit keyword.
            if (input == null || input.toLowerCase().contains("exit")) {
                System.exit(1);
            }

            // Produce AST From source code
            Ast program = parser.produceAST(input);
            System.out.println(ObjectPrinter.deserializeObjectToString(program));

            Object results = new Interpreter().evaluate(program);
            System.out.println(ObjectPrinter.deserializeObjectToString(results));

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