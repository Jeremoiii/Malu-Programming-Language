package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

public class MemberExpr extends Expression {
    private Expression object;
    private Expression property;
    private boolean computed;

    public MemberExpr() {
        this.kind = NodeType.MEMBER_EXPR;
    }

    public Expression getObject() {
        return object;
    }

    public void setObject(Expression object) {
        this.object = object;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }

    public boolean isComputed() {
        return computed;
    }

    public void setComputed(boolean computed) {
        this.computed = computed;
    }
}
