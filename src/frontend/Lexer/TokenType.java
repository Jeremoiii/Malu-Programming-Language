package frontend.Lexer;

public enum TokenType {
    // Literal Types
    NUMBER,
    IDENTIFIER,

    // Keywords
    LET,
    CONST,
    FUNCTION,

    // Grouping * Operators
    BINARY_OPERATOR,
    EQUALS,
    COMMA,
    DOT,
    COLON,
    SEMICOLON,

    // ()
    OPEN_PAREN,
    CLOSE_PAREN,

    // {}
    OPEN_BRACE,
    CLOSE_BRACE,

    // []
    OPEN_BRACKET,
    CLOSE_BRACKET,

    END_OF_FILE
}
