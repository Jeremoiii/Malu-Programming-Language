package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class Property extends Expression {
    private String key;
    private Expression value;
    private NodeType kind;

    public Property() {
        this.kind = NodeType.PROPERTY;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public NodeType getKind() {
        return this.kind;
    }
}