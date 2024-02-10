package frontend.AST.declarations;

import frontend.AST.Expression;
import frontend.AST.NodeType;
import frontend.AST.Statement;

public class VariableDeclaration extends Statement {
    private boolean constant;
    private String identifier;
    private Expression value;

    public VariableDeclaration() {
        this.kind = NodeType.VARIABLE_DECLARATION;
    }

    public boolean isConstant() {
        return constant;
    }

    public void setConstant(boolean constant) {
        this.constant = constant;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }
}