package classes;

import java.util.*;

public class Lexer {

    public enum TokenType {
        NullLiteral,
        Number,
        Identifier,
        Equals,
        OpenParen, CloseParen,
        BinaryOperator,
        Let,
        EndOfFile
    }

    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("let", TokenType.Let);
        KEYWORDS.put("null", TokenType.NullLiteral);
    }

    // convert to record?
    public static class Token {
        private final String value;
        private final TokenType type;

        public Token(String value, TokenType type) {
            this.value = value;
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public TokenType getType() {
            return type;
        }
    }

    public List<Token> tokenize(String sourceCode) {
        List<Token> tokens = new ArrayList<>();
        char[] src = sourceCode.toCharArray();

        while (src.length > 0) {
            if (src[0] == '(') {
                tokens.add(new Token(String.valueOf(src[0]), TokenType.OpenParen));
                src = copyArrayWithoutFirstElement(src);
                continue;
            }

            if (src[0] == ')') {
                tokens.add(new Token(String.valueOf(src[0]), TokenType.CloseParen));
                src = copyArrayWithoutFirstElement(src);
                continue;
            }

            if (src[0] == '+' || src[0] == '-' || src[0] == '*' || src[0] == '/' || src[0] == '%') {
                tokens.add(new Token(String.valueOf(src[0]), TokenType.BinaryOperator));
                src = copyArrayWithoutFirstElement(src);
                continue;
            }

            if (src[0] == '=') {
                tokens.add(new Token(String.valueOf(src[0]), TokenType.Equals));
                src = copyArrayWithoutFirstElement(src);
                continue;
            }

            if (isInt(src[0])) {
                StringBuilder number = new StringBuilder();
                while (src.length > 0 && isInt(src[0])) {
                    number.append(src[0]);
                    src = copyArrayWithoutFirstElement(src);
                }

                tokens.add(new Token(number.toString(), TokenType.Number));
                continue;
            }

            if (isAlpha(src[0])) {
                StringBuilder identifier = new StringBuilder();
                while (src.length > 0 && isAlpha(src[0])) {
                    identifier.append(src[0]);
                    src = copyArrayWithoutFirstElement(src);
                }

                TokenType reserved = KEYWORDS.get(identifier.toString());

                // Weitere Checks durchführen, ob reserved auch vom Typ 'number' ist ?
                tokens.add(new Token(identifier.toString(), Objects.requireNonNullElse(reserved, TokenType.Identifier)));

                continue;
            }

            if (isSkippable(src[0])) {
                src = copyArrayWithoutFirstElement(src);
                continue;
            }

            System.out.println("Invalid character found in source: " + src[0]);
            break;
        }

        tokens.add(new Token("EndOfFile", TokenType.EndOfFile));

        return tokens;
    }

    private char[] copyArrayWithoutFirstElement(char[] array) {
        char[] newArray = new char[array.length - 1];
        System.arraycopy(array, 1, newArray, 0, newArray.length);
        return newArray;
    }

    private boolean isAlpha(char src) {
        return Character.isLetter(src);
    }

    private boolean isInt(char src) {
        return Character.isDigit(src);
    }

    private boolean isSkippable(char src) {
        return src == ' ' || src == '\n' || src == '\t';
    }
}
