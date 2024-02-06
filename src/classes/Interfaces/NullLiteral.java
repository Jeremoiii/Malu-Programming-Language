package classes.Interfaces;

import classes.Interfaces.Expression;
import classes.Types.NodeType;

public class NullLiteral extends Expression {
    private final String value = "null";
    private NodeType kind;

    public NullLiteral() {
        this.kind = NodeType.NULL_LITERAL;
    }

    @Override
    public NodeType getKind() {
        return kind;
    }

    public String getValue() {
        return value;
    }
}
