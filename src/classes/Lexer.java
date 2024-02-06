package classes;

import java.util.*;

public class Lexer {
    enum TokenType {
        Null,
        Number,
        Identifier,
        Equals,
        OpenParen, CloseParen,
        BinaryOperator,
        Let,
        EndOfFile
    }

    public static class Token {
        String value;
        TokenType type;

        Token(String value, TokenType type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return this.value;
        }

        public TokenType getType() {
            return this.type;
        }
    }

    private static final Map<String, TokenType> KEYWORDS = new HashMap<String, TokenType>() {{
        put("let", TokenType.Let);
        put("null", TokenType.Null);
    }};

    private static boolean isAlpha(String src) {
        return !src.toUpperCase().equals(src.toLowerCase());
    }

    private static boolean isInt(String str) {
        int c = str.charAt(0);
        int[] bounds = {'0', '9'};

        return (c >= bounds[0] && c <= bounds[1]);
    }

    private static boolean isSkippable(String str) {
        return str.equals(" ") || str.equals("\n") || str.equals("\t");
    }

    public static List<Token> tokenize(String sourceCode) {
        List<Token> tokens = new ArrayList<>();
        List<String> src = new ArrayList<>(Arrays.asList(sourceCode.split("")));

        while (!src.isEmpty()) {
            if (src.get(0).equals("(")) {
                tokens.add(new Token(src.remove(0), TokenType.OpenParen));
                continue;
            }

            // ... repeat for other token types ...

            if (isSkippable(src.get(0))) {
                src.remove(0);
                continue;
            }

            System.out.println("Invalid character found in source: " + src.get(0));
            break;
        }

        tokens.add(new Token("EndOfFile", TokenType.EndOfFile));

        return tokens;
    }
}