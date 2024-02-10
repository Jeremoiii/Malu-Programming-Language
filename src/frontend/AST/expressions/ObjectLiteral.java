package frontend.AST.expressions;

import frontend.AST.Expression;
import frontend.AST.NodeType;

import java.util.List;

public class ObjectLiteral extends Expression {
    private List<Property> properties;

    public ObjectLiteral() {
        this.kind = NodeType.OBJECT_LITERAL;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
