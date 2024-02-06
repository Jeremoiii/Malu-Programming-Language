package classes;

import classes.Interfaces.Statement;
import classes.Types.NodeType;

import java.util.List;

public class Ast extends Statement {

    private NodeType kind;
    private List<Statement> body;

    public Ast() {
        this.kind = NodeType.PROGRAM;
    }

    @Override
    public NodeType getKind() {
        return kind;
    }

    public void setKind(NodeType kind) {
        this.kind = kind;
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }
}