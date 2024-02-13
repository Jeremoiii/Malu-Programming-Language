package frontend.AST;

//TODO: mit generischer Klasse ersetzen.
import java.util.ArrayList;
import java.util.List;

public class Program extends Statement {
    private List<Statement> body;

    public Program() {
        this.kind = NodeType.PROGRAM;
        this.body = new ArrayList<>(); // Initialize the body list
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }
}