package classes.Interfaces;

public record NumericLiteral(double value) implements Expression {

    @Override
    public String getKind() {
        return "NumericLiteral";
    }
}
