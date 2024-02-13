package frontend.AST.declarations;

import frontend.AST.NodeType;
import frontend.AST.Statement;

import java.util.List;

public class FunctionDeclaration extends Statement {
    private List<String> parameters;
    private String name;

    private List<Statement> body;

    public FunctionDeclaration() {
        this.kind = NodeType.FUNCTION_DECLARATION;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }
}