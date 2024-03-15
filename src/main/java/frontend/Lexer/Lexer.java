package frontend.Lexer;

import buffer.StringBuffer;

import java.util.*;

public class Lexer {
    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();
    static {
        KEYWORDS.put("let", TokenType.LET);
        KEYWORDS.put("const", TokenType.CONST);
        KEYWORDS.put("func", TokenType.FUNCTION);
    }

    private static boolean isAlpha(char c) {
        return Character.isLetter(c);
    }

    private static boolean isSkippable(char c) {
        return Character.isWhitespace(c);
    }

    private static boolean isInt(char c) {
        return Character.isDigit(c);
    }

    public static List<Token> tokenize(String sourceCode) {
        List<Token> tokens = new ArrayList<>();
        Queue<Character> src = new LinkedList<>();
        for (char c : sourceCode.toCharArray()) src.add(c);

        while (!src.isEmpty()) {
            char c = src.peek();
            if (c == '(') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.OPEN_PAREN));
                continue;
            }
            if (c == ')') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.CLOSE_PAREN));
                continue;
            }
            if (c == '{') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.OPEN_BRACE));
                continue;
            }
            if (c == '}') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.CLOSE_BRACE));
                continue;
            }
            if (c == '[') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.OPEN_BRACKET));
                continue;
            }
            if (c == ']') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.CLOSE_BRACKET));
                continue;
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.BINARY_OPERATOR));
                continue;
            }
            if (c == '=') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.EQUALS));
                continue;
            }
            if (c == ';') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.SEMICOLON));
                continue;
            }
            if (c == ':') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.COLON));
                continue;
            }
            if (c == ',') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.COMMA));
                continue;
            }
            if (c == '.') {
                tokens.add(new Token(String.valueOf(src.poll()), TokenType.DOT));
                continue;
            }
            if (c == '"') {
                src.poll();
                StringBuilder string = new StringBuilder();
                while (!src.isEmpty() && src.peek() != '"') {
                    string.append(src.poll());
                }
                src.poll();
                tokens.add(new Token(string.toString(), TokenType.STRING));
                continue;
            }

            if (isInt(c)) {
                StringBuilder number = new StringBuilder();
                while (!src.isEmpty() && isInt(src.peek())) {
                    number.append(src.poll());
                }
                tokens.add(new Token(number.toString(), TokenType.NUMBER));
                continue;
            }

            if (isAlpha(c)) {
                StringBuilder identifier = new StringBuilder();
                while (!src.isEmpty() && isAlpha(src.peek())) {
                    identifier.append(src.poll());
                }
                TokenType reserved = KEYWORDS.get(identifier.toString());
                tokens.add(new Token(identifier.toString(), reserved != null ? reserved : TokenType.IDENTIFIER));
                continue;
            }

            if (isSkippable(c)) {
                src.poll();
                continue;
            }

            StringBuffer.getInstance().append("Invalid character found in source: " + c);
            break;
        }

        tokens.add(new Token("EndOfFile", TokenType.END_OF_FILE));

        return tokens;
    }
}