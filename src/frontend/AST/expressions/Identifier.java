package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class Identifier extends Expression {
    private String symbol;

    public Identifier() {
        this.kind = NodeType.IDENTIFIER;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public NodeType getKind() {
        return kind;
    }
}