package classes.Interfaces;

import classes.Interfaces.Expression;

public class NullLiteral implements Expression {
    private final String value;

    public NullLiteral() {
        this.value = "null";
    }

    @Override
    public String getKind() {
        return "NullLiteral";
    }

    public String getValue() {
        return value;
    }
}
