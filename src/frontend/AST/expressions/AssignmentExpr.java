package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class AssignmentExpr extends Expression {
    private Expression assigne;
    private Expression value;

    public AssignmentExpr() {
        this.kind = NodeType.ASSIGNMENT_EXPR;
    }

    public Expression getAssigne() {
        return assigne;
    }

    public void setAssigne(Expression assigne) {
        this.assigne = assigne;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }
}
