package frontend.AST;

public abstract class Statement extends AST {

    protected NodeType kind;

    public NodeType getKind() {
        return kind;
    }

    public void setKind(NodeType kind) {
        this.kind = kind;
    }
}
