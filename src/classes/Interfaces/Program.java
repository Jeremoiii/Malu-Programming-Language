package classes.Interfaces;

import classes.Types.NodeType;

public class Program extends Statement {
    private final NodeType kind = NodeType.PROGRAM;
    private final Statement[] body;

    public Program(Statement[] body) {
        this.body = body;
    }

    @Override
    public NodeType getKind() {
        return kind;
    }

    public Statement[] getBody() {
        return body;
    }
}