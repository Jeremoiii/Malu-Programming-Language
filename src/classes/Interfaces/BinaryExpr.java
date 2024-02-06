package classes.Interfaces;

import classes.Types.NodeType;

public class BinaryExpr extends Expression {
    private Expression left;
    private Expression right;
    private String operator;
    private NodeType kind;

    public BinaryExpr(Expression left, Expression right, String operator) {
        this.kind = NodeType.BINARY_EXPR;
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public NodeType getKind() {
        return kind;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
