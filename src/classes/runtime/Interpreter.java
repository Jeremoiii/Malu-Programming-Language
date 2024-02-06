package classes.runtime;

import classes.Interfaces.BinaryExpr;
import classes.Interfaces.Program;
import classes.Interfaces.Statement;
import classes.Interfaces.NumericLiteral;
import classes.Types.NodeType;
import classes.runtime.interfaces.NullValue;
import classes.runtime.interfaces.NumberValue;
import classes.runtime.interfaces.RuntimeValue;

public class Interpreter {
    public static RuntimeValue evaluateProgram(Program program) {
        RuntimeValue lastEvaluated = new NullValue();

        for (Statement statement : program.body) {
            lastEvaluated = evaluate(statement);
        }

        return lastEvaluated;
    }

    public static RuntimeValue evaluateBinaryExpression(BinaryExpr binOperation) {
        RuntimeValue left = evaluate(binOperation.getLeft());
        RuntimeValue right = evaluate(binOperation.getRight());

        if (left.getType().equals("number") && right.getType().equals("number")) {
            return evaluateNumericExpression((NumberValue) left, (NumberValue) right, binOperation.getOperator());
        }

        return new NullValue();
    }

    public static NumberValue evaluateNumericExpression(NumberValue left, NumberValue right, String operator) {
        float result = 0;

        switch (operator) {
            case "+":
                result = (float) (left.getValue() + right.getValue());
                break;
            case "-":
                result = (float) (left.getValue() - right.getValue());
                break;
            case "*":
                result = (float) (left.getValue() * right.getValue());
                break;
            case "/":
                if (right.getValue() == 0) {
                    System.err.println("Trying to divide by 0 is not allowed!");
                    System.exit(1);
                }
                result = (float) (left.getValue() / right.getValue());
                break;
            case "%":
                result = (float) (left.getValue() % right.getValue());
                break;
        }

        return new NumberValue(result);
    }

    public static RuntimeValue evaluate(Statement astNode) {
        switch (astNode.kind) {
            case NodeType.NUMERIC_LITERAL:
                return new NumberValue(((NumericLiteral) astNode).getValue());

            case NodeType.NULL_LITERAL:
                return new NullValue();

            case NodeType.BINARY_EXPR:
                return evaluateBinaryExpression((BinaryExpr) astNode);

            case NodeType.PROGRAM:
                return evaluateProgram((Program) astNode);

            default:
                System.err.println("This AST Node has not yet been setup for interpretation." + astNode);
                System.exit(1);
                return null; // This will never be reached, but Java requires a return statement
        }
    }
}