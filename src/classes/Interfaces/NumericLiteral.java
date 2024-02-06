package classes.Interfaces;

import classes.Types.NodeType;

public class NumericLiteral extends Expression {
    private float value;
    private NodeType kind;

    public NumericLiteral(float number) {
        this.kind = NodeType.NUMERIC_LITERAL;
        this.value = number;
    }

    @Override
    public NodeType getKind() {
        return this.kind;
    }

    public float getValue() {
        return value;
    }
}