package classes.runtime;

import classes.Interfaces.BinaryExpr;
import classes.Interfaces.NumericLiteral;
import classes.Interfaces.Program;
import classes.Interfaces.Statement;
import classes.runtime.interfaces.NullValue;
import classes.runtime.interfaces.NumberValue;
import classes.runtime.interfaces.RuntimeValue;

public class Interpreter {

    public RuntimeValue evaluateProgram(Program program) {
        RuntimeValue lastEvaluated = null;
        for (Statement statement : program.body()) {
            lastEvaluated = evaluate(statement);
        }
        return (NumberValue) lastEvaluated;
    }

    public RuntimeValue evaluateBinaryExpression(BinaryExpr binOperation) {
        RuntimeValue left = evaluate(binOperation.left());
        RuntimeValue right = evaluate(binOperation.right());

        left.getType();

        return new NullValue();
    }

    public NumberValue evaluateNumericExpression(NumberValue left, NumberValue right, String operator) {
        double result = switch (operator) {
            case "+" -> left.getValue() + right.getValue();
            case "-" -> left.getValue() - right.getValue();
            case "*" -> left.getValue() * right.getValue();
            case "/" -> {
                if (right.getValue() == 0) {
                    System.err.println("Trying to divide by 0 is not allowed!");
                    System.exit(1);
                }
                yield left.getValue() / right.getValue();
            }
            case "%" -> left.getValue() % right.getValue();
            default -> 0;
        };

        return new NumberValue(result);
    }

    public RuntimeValue evaluate(Statement astNode) {
        switch (astNode.getKind()) {
            case "NumericLiteral":
                return new NumberValue(((NumericLiteral) astNode).value());

            case "NullLiteral":
                return new NullValue();

            case "BinaryExpr":
                return evaluateBinaryExpression((BinaryExpr) astNode);

            case "Program":
                return evaluateProgram((Program) astNode);

            default:
                System.err.println("This AST Node has not yet been set up for interpretation: " + astNode.getKind());
                System.exit(1);
                return new NullValue();
        }
    }
}
