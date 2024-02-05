package classes.Interfaces;

import classes.Interfaces.Expression;

public class Identifier implements Expression {
    private final String symbol;
    private final String kind = "Identifier";

    public Identifier(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getKind() {
        return kind;
    }

    public String getSymbol() {
        return symbol;
    }
}
