package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

import java.util.List;

public class CallExpr extends Expression {
    private List<Expression> args;

    private Expression caller;

    public CallExpr() {
        this.kind = NodeType.CALL_EXPR;
    }

    public List<Expression> getArgs() {
        return args;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
    }

    public Expression getCaller() {
        return caller;
    }

    public void setCaller(Expression caller) {
        this.caller = caller;
    }
}
