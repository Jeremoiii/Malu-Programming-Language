package classes;

import classes.Interfaces.*;
import classes.Types.NodeType;

import java.util.*;

public class Parser {
    private List<Lexer.Token> tokens = new ArrayList<>();

    private boolean notEndOfFile() {
        return tokens.get(0).type != Lexer.TokenType.EndOfFile;
    }

    private Lexer.Token at() {
        return tokens.get(0);
    }

    private Lexer.Token next() { // eat token
        return tokens.remove(0);
    }

    private void expect(Lexer.TokenType type, String err) {
        Lexer.Token prev = tokens.remove(0);

        if (prev == null || prev.type != type) {
            System.out.println("Parser Error:\n" + err + prev + "- Expecting: " + type);
            System.exit(1);
        }
    }

    private Statement parseStatement() {
        return parseExpression();
    }

    private Expression parseExpression() {
        return parseAdditiveExpression();
    }

    private Expression parseAdditiveExpression() {
        Expression left = parseMultiplicativeExpression();

        while (at().value.equals("+") || at().value.equals("-")) {
            String operator = next().value;
            Expression right = parseMultiplicativeExpression();

            left = new BinaryExpr(left, right, operator);
        }

        return left;
    }

    private Expression parseMultiplicativeExpression() {
        Expression left = parsePrimaryExpression();

        while (at().value.equals("/") || at().value.equals("*") || at().value.equals("%")) {
            String operator = next().value;
            Expression right = parsePrimaryExpression();

            left = new BinaryExpr(left, right, operator);
        }

        return left;
    }

    private Expression parsePrimaryExpression() {
        Lexer.TokenType token = at().type;

        switch (token) {
            case Identifier:
                return new Identifier(next().value);

            case Null:
                next();
                return new NullLiteral();

            case Number:
                return new NumericLiteral(Float.parseFloat(next().value));

            case OpenParen:
                next();
                Expression value = parseExpression();
                expect(Lexer.TokenType.CloseParen, "Unexpected token found inside parenthesised expression. Expected closing parenthesis.");
                return value;

            default:
                System.err.println("Unexpected token found during parsing!" + at());
                System.exit(1);
                return null; // This will never be reached, but Java requires a return statement
        }
    }

    public Program produceAST(String sourceCode) {
        tokens = Lexer.tokenize(sourceCode);

        Program program = new Program();
        program.kind = NodeType.PROGRAM;
        program.body = new ArrayList<>();

        while (notEndOfFile()) {
            program.body.add(parseStatement());
        }

        return program;
    }
}