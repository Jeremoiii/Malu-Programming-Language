package frontend.AST;

public enum NodeType {
    // Statements
    PROGRAM,
    VARIABLE_DECLARATION,
    FUNCTION_DECLARATION,

    // Expressions
    ASSIGNMENT_EXPR,
    MEMBER_EXPR,
    CALL_EXPR,

    // Literals
    PROPERTY,
    OBJECT_LITERAL,
    NUMERIC_LITERAL,
    IDENTIFIER,
    BINARY_EXPR
}
