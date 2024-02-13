package runtime.values;

import frontend.AST.Statement;
import runtime.Environment;
import runtime.RuntimeValue;

import java.util.List;

public class FunctionValue extends RuntimeValue {
    private String name;
    private List<String> parameters;
    private Environment declarationEnv;
    private List<Statement> body;

    public FunctionValue(String name, List<String> parameters, Environment declarationEnv, List<Statement> body) {
        setType(ValueTypes.FUNCTION);
        this.name = name;
        this.parameters = parameters;
        this.declarationEnv = declarationEnv;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public Environment getDeclarationEnv() {
        return declarationEnv;
    }

    public void setDeclarationEnv(Environment declarationEnv) {
        this.declarationEnv = declarationEnv;
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
