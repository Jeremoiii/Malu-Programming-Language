package frontend.Lexer;

public class Token {
    String value;
    TokenType type;

    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
