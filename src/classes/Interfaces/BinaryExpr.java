package classes.Interfaces;

public record BinaryExpr(Expression left, Expression right, String operator) implements Expression {

    @Override
    public String getKind() {
        return "BinaryExpr";
    }

}
