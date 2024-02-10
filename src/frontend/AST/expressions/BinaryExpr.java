package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class BinaryExpr extends Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public BinaryExpr() {
        this.kind = NodeType.BINARY_EXPR;
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