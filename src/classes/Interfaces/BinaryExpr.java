package classes.Interfaces;

import classes.Interfaces.Expression;

public class BinaryExpr implements Expression {
    private final Expression left;
    private final Expression right;
    private final String operator;

    private final String kind = "BinaryExpr";

    public BinaryExpr(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String getKind() {
        return kind;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }

}
