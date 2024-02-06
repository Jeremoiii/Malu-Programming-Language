package classes.Interfaces;

import java.util.List;

public record Program(List<Statement> body) implements Statement {

    public String getKind() {
        return "Program";
    }
}