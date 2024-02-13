package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class StringLiteral extends Expression {
    private String value;
    public StringLiteral() {
        this.kind = NodeType.STRING_LITERAL;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}