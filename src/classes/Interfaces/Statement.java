package classes.Interfaces;

import classes.Types.NodeType;

public abstract class Statement {
    public NodeType kind;

    public abstract NodeType getKind();
}