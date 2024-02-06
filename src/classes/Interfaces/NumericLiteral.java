package classes.Interfaces;

import classes.Interfaces.Expression;

public class NumericLiteral implements Expression {
    private final double value;
    private final String kind = "NumericLiteral";

    public NumericLiteral(double value) {
        this.value = value;
    }

    @Override
    public String getKind() {
        return kind;
    }

    public double getValue() {
        return value;
    }
}
