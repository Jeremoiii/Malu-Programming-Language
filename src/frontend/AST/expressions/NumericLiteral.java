package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class NumericLiteral extends Expression {
    private float value;
    public NumericLiteral() {
        this.kind = NodeType.NUMERIC_LITERAL;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}