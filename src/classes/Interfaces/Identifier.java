package classes.Interfaces;

import classes.Types.NodeType;

import java.security.Key;

public class Identifier extends Expression {
    private String symbol;
    private NodeType kind;

    public Identifier(String symbol) {
        this.kind = NodeType.IDENTIFIER;
        this.symbol = symbol;
    }

    @Override
    public NodeType getKind() {
        return kind;
    }

    public String getSymbol() {
        return symbol;
    }
}
