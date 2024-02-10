package frontend.Parser;

import frontend.AST.Expression;
import frontend.AST.Program;
import frontend.AST.Statement;
import frontend.AST.declarations.FunctionDeclaration;
import frontend.AST.declarations.VariableDeclaration;
import frontend.AST.expressions.*;
import frontend.Lexer.Lexer;
import frontend.Lexer.Token;
import java.util.ArrayList;
import java.util.List;
import frontend.Lexer.TokenType;

public class Parser {
    private List<Token> tokens = new ArrayList<>();

    private boolean notEndOfFile() {
        return tokens.get(0).getType() != TokenType.END_OF_FILE;
    }

    private Token at() {
        return tokens.get(0);
    }

    private Token next() { // eat token
        return tokens.remove(0);
    }

    private Token expect(TokenType type, String err) {
        Token prev = tokens.remove(0);

        if (prev == null || prev.getType() != type) {
            System.out.println("Parser Error:\n" + err + prev + "- Expecting: " + type);
            System.exit(1);
        }

        return prev;
    }

    public Program produceAST(String sourceCode) {
        this.tokens = Lexer.tokenize(sourceCode);

        Program program = new Program();

        while (this.notEndOfFile()) {
            program.getBody().add(this.parseStatement());
        }

        return program;
    }

    private Statement parseStatement() {
        switch (this.at().getType()) {
            case LET:
            case CONST:
                return this.parseVariableDeclaration();

            case FUNCTION:
                return this.parseFunctionDeclaration();

            default:
                return this.parseExpression();
        }
    }

    private Statement parseFunctionDeclaration() {
        this.next();
        String name = this.expect(TokenType.IDENTIFIER, "Expected function name following fn keyword.").getValue();
        List<Expression> args = this.parseArgs();
        List<String> params = new ArrayList<>();

        for (Statement arg : args) {
            if (!(arg instanceof Identifier)) {
                throw new RuntimeException("Function parameters must be identifiers.");
            }

            params.add(((Identifier) arg).getSymbol());
        }

        this.expect(TokenType.OPEN_BRACE, "Expected opening brace following function declaration.");

        List<Statement> body = new ArrayList<>();

        while (this.at().getType() != TokenType.END_OF_FILE && this.at().getType() != TokenType.CLOSE_BRACE) {
            body.add(this.parseStatement());
        }

        this.expect(TokenType.CLOSE_BRACE, "Expected closing brace following function declaration.");

        FunctionDeclaration func = new FunctionDeclaration();
        func.setBody((Statement) body);
        func.setName(name);
        func.setParameters(params);

        return func;
    }

    private Statement parseVariableDeclaration() {
        boolean isConstant = this.next().getType() == TokenType.CONST;
        String identifier = this.expect(TokenType.IDENTIFIER, "Expected identifier name following let | const keywords.").getValue();

        if (this.at().getType() == TokenType.SEMICOLON) {
            this.next();

            if (isConstant) {
                throw new RuntimeException("Must assign a value to a constant variable.");
            }

            VariableDeclaration varDecl = new VariableDeclaration();
            varDecl.setIdentifier(identifier);
            varDecl.setConstant(false);

            return varDecl;
        }

        this.expect(TokenType.EQUALS, "Expected equals sign following variable declaration.");

        VariableDeclaration declaration = new VariableDeclaration();
        declaration.setValue(this.parseExpression());
        declaration.setIdentifier(identifier);
        declaration.setConstant(isConstant);

        this.expect(TokenType.SEMICOLON, "Expected semicolon following variable declaration.");

        return declaration;
    }

    private Expression parseExpression() {
        return this.parseAssignmentExpression();
    }

    private Expression parseAssignmentExpression() {
        Expression left = this.parseObjectExpression();

        if (this.at().getType() == TokenType.EQUALS) {
            this.next();
            Expression value = this.parseAssignmentExpression();

            AssignmentExpr assignmentExpr = new AssignmentExpr();
            assignmentExpr.setValue(value);
            assignmentExpr.setAssigne(left);

            return assignmentExpr;
        }

        return left;
    }

    private Expression parseObjectExpression() {
        if (this.at().getType() != TokenType.OPEN_BRACE) {
            return this.parseAdditiveExpression();
        }

        this.next();
        List<Property> properties = new ArrayList<>();

        while (this.notEndOfFile() && this.at().getType() != TokenType.CLOSE_BRACE) {
            String key = this.expect(TokenType.IDENTIFIER, "Object literal missing key name.").getValue();

            if (this.at().getType() == TokenType.COMMA) {
                this.next();
                Property property = new Property();
                property.setKey(key);
                properties.add(property);
                continue;
            }

            if (this.at().getType() == TokenType.CLOSE_BRACE) {
                Property property = new Property();
                property.setKey(key);
                properties.add(property);
                continue;
            }

            this.expect(TokenType.COLON, "Object literal missing colon following identifier in ObjectExpr");
            Expression value = this.parseExpression();

            Property property = new Property();
            property.setValue(value);
            property.setKey(key);
            properties.add(property);

            if (this.at().getType() != TokenType.CLOSE_BRACE) {
                this.expect(TokenType.COMMA, "Expected comma or closing bracket following property.");
            }
        }

        this.expect(TokenType.CLOSE_BRACE, "Object literal missing closing bracket.");

        ObjectLiteral objectLiteral = new ObjectLiteral();
        objectLiteral.setProperties(properties);

        return objectLiteral;
    }

    private Expression parseAdditiveExpression() {
        Expression left = this.parseMultiplicativeExpression();

        while ("+".equals(this.at().getValue()) || "-".equals(this.at().getValue())) {
            String operator = this.next().getValue();
            Expression right = this.parseMultiplicativeExpression();

            BinaryExpr binaryExpr = new BinaryExpr();
            binaryExpr.setLeft(left);
            binaryExpr.setRight(right);
            binaryExpr.setOperator(operator);

            left = binaryExpr;
        }

        return left;
    }

    private Expression parseMultiplicativeExpression() {
        Expression left = this.parseCallMemberExpression();

        while ("/".equals(this.at().getValue()) || "*".equals(this.at().getValue()) || "%".equals(this.at().getValue())) {
            String operator = this.next().getValue();
            Expression right = this.parseCallMemberExpression();

            BinaryExpr binaryExpr = new BinaryExpr();
            binaryExpr.setLeft(left);
            binaryExpr.setRight(right);
            binaryExpr.setOperator(operator);

            left = binaryExpr;
        }

        return left;
    }

    private Expression parseCallMemberExpression() {
        Expression member = this.parseMemberExpression();

        if (this.at().getType() == TokenType.OPEN_PAREN) {
            return this.parseCallExpression(member);
        }

        return member;
    }

    private Expression parseCallExpression(Expression caller) {
        CallExpr callExpression = new CallExpr();
        callExpression.setCaller(caller);
        callExpression.setArgs(this.parseArgs());

        if (this.at().getType() == TokenType.OPEN_PAREN) {
            callExpression = (CallExpr) this.parseCallExpression(callExpression);
        }

        return callExpression;
    }

    private List<Expression> parseArgs() {
        this.expect(TokenType.OPEN_PAREN, "Expected opening parenthesis following function call.");
        List<Expression> args = this.at().getType() == TokenType.CLOSE_PAREN ? new ArrayList<>() : this.parseArgumentList();

        this.expect(TokenType.CLOSE_PAREN, "Expected closing parenthesis following function call.");
        return args;
    }

    private List<Expression> parseArgumentList() {
        List<Expression> args = new ArrayList<>();
        args.add(this.parseAssignmentExpression());

        while ((this.at().getType() == TokenType.COMMA) && this.next() != null) {
            args.add(this.parseAssignmentExpression());
        }

        return args;
    }

    private Expression parseMemberExpression() {
        Expression object = this.parsePrimaryExpression();

        while (this.at().getType() == TokenType.DOT || this.at().getType() == TokenType.OPEN_BRACKET) {
            Token operator = this.next();
            Expression property;
            boolean computed;

            if (operator.getType() == TokenType.DOT) {
                computed = false;
                property = this.parsePrimaryExpression();

                if (!"Identifier".equals(((Property) property).getKind())) {
                    throw new RuntimeException("Cannot use dot operator with right hand side being an identifier.");
                }
            } else {
                computed = true;
                property = this.parseExpression();

                this.expect(TokenType.CLOSE_BRACKET, "Expected closing bracket following computed property.");
            }

            MemberExpr memberExpr = new MemberExpr();
            memberExpr.setObject(object);
            memberExpr.setProperty(property);
            memberExpr.setComputed(computed);

            object = memberExpr;
        }

        return object;
    }

    private Expression parsePrimaryExpression() {
        TokenType token = this.at().getType();

        switch (token) {
            case IDENTIFIER:
                Identifier identifier = new Identifier();
                identifier.setSymbol(this.next().getValue());
                return identifier;

            case NUMBER:
                NumericLiteral numericLiteral = new NumericLiteral();
                numericLiteral.setValue(Float.parseFloat(this.next().getValue()));
                return numericLiteral;

            case OPEN_PAREN:
                this.next();
                Expression value = this.parseExpression();
                this.expect(TokenType.CLOSE_PAREN, "Unexpected token found inside parenthesised expression. Expected closing parenthesis.");
                return value;

            default:
                System.err.println("Unexpected token found during parsing! " + this.at());
                System.exit(1);
                return null; // This line is required as all paths in the function need to return a value.
        }
    }
}
