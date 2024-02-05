package classes;

import classes.Interfaces.Statement;

import java.util.List;

public class Ast implements Statement {

    private String kind;
    private List<Statement> body;

    public Ast() {
        this.kind = "Program";
    }

    @Override
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }

    public void listAst() {
        System.out.println("AST Kind: " + kind);
        listBody(body);
    }

    private void listBody(List<Statement> statements) {
        for (Statement statement : statements) {
            String indent = "  ".repeat(1);
            System.out.println(indent + "- " + statement.getKind());

            // Wenn es sich um ein Ast-Objekt handelt, rufe listBody rekursiv auf
            if (statement instanceof Ast nestedAst) {
                nestedAst.listAst();
            }
        }
    }
}