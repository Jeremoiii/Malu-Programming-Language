package classes;

import classes.Interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Lexer.Token> tokens = new ArrayList<>();

    private boolean notEndOfFile() {
        return tokens.get(0).getType() != Lexer.TokenType.EndOfFile;
    }

    private Lexer.Token at() {
        return tokens.get(0);
    }

    private Lexer.Token next() {
        Lexer.Token previous = tokens.remove(0);
        System.out.println(previous);
        return previous;
    }

    private void expect(Lexer.TokenType type, String err) {
        if (tokens.isEmpty()) {
            System.out.println("Parser Error:\n" + err + " - Expecting: " + type);
            return;
        }

        Lexer.Token prev = tokens.remove(0);

        if (prev.getType() != type) {
            System.out.println("Parser Error:\n" + err + " " + prev + " - Expecting: " + type);
            System.exit(0);
        }
    }

    private Statement parseStatement() {
        return this.parseExpression();
    }

    private Expression parseExpression() {
        return this.parseAdditiveExpression();
    }

    private Expression parseAdditiveExpression() {
        Expression left = this.parseMultiplicativeExpression();

        while (this.at().getValue().equals("+") || this.at().getValue().equals("-")) {
            String operator = this.next().getValue();
            Expression right = this.parseMultiplicativeExpression();

            left = new BinaryExpr(left, right, operator);
        }

        return left;
    }

    private Expression parseMultiplicativeExpression() {
        Expression left = this.parsePrimaryExpression();

        while (this.at().getValue().equals("/") || this.at().getValue().equals("*") || this.at().getValue().equals("%")) {
            String operator = this.next().getValue();
            Expression right = this.parsePrimaryExpression();

            left = new BinaryExpr(left, right, operator);
        }

        return left;
    }

    private Expression parsePrimaryExpression() {
        Lexer.Token token = at();

        switch (token.getType()) {
            case Identifier:
                return new Identifier(next().getValue());

            case NullLiteral:
                this.next();
                return new NullLiteral();

            case Number:
                return new NumericLiteral(Double.parseDouble(next().getValue()));

            case OpenParen:
                this.next();
                Expression value = this.parseExpression();
                this.expect(Lexer.TokenType.CloseParen, "Unexpected token found during parsing. Expected closing parent!");
                return value;

            default:
                System.err.println("Unexpected token found during parsing! " + at().getValue());
                System.exit(1);
                return null;
        }
    }

    public Ast produceAST(String sourceCode) {
        tokens = new Lexer().tokenize(sourceCode);

        Ast ast = new Ast();
        ast.setKind("Program");
        ast.setBody(new ArrayList<>());

        while (notEndOfFile()) {
            ast.getBody().add(parseStatement());
        }

        return ast;
    }
}
