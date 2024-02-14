package runtime.evaluations;

import buffer.StringBuffer;
import frontend.AST.Expression;
import frontend.AST.NodeType;
import frontend.AST.Statement;
import frontend.AST.expressions.*;
import runtime.Environment;
import runtime.Interpreter;
import runtime.RuntimeValue;
import runtime.values.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import static java.lang.System.*;
import static runtime.Interpreter.evaluate;


public class ExpressionEvaluator {
    public static NumberValue evaluateNumericExpression(NumberValue left, NumberValue right, String operator) {
        double result = switch (operator) {
            case "+" -> left.getValue() + right.getValue();
            case "-" -> left.getValue() - right.getValue();
            case "*" -> left.getValue() * right.getValue();
            case "/" -> {
                if (right.getValue() == 0) {
                    err.println("Trying to divide by 0 is not allowed!");
//                    StringBuffer.getInstance().append("Trying to divide by 0 is not allowed!");
                    exit(1);
                }
                yield left.getValue() / right.getValue();
            }
            case "%" -> left.getValue() % right.getValue();
            default -> 0;
        };

        return new NumberValue(result);
    }

    public static RuntimeValue evaluateBinaryExpression(BinaryExpr binOperation, Environment env) {
        RuntimeValue left = evaluate(binOperation.getLeft(), env);
        RuntimeValue right = evaluate(binOperation.getRight(), env);

        if (left.getType().equals(RuntimeValue.ValueTypes.NUMBER) && right.getType().equals(RuntimeValue.ValueTypes.NUMBER)) {
            return evaluateNumericExpression((NumberValue) left, (NumberValue) right, binOperation.getOperator());
        }

        return new NullValue();
    }

    public static RuntimeValue evaluateIdentifier(Identifier identifier, Environment env) {
        return env.lookupVariable(identifier.getSymbol());
    }

    public static RuntimeValue evaluateAssignment(AssignmentExpr node, Environment env) {
        if (!node.getAssigne().getKind().equals(NodeType.IDENTIFIER)) {
            throw new IllegalArgumentException("Invalid LHS inside assignment expr " + node.getAssigne().toString());
        }

        String varName = ((Identifier) node.getAssigne()).getSymbol();
        return env.assignVariable(varName, evaluate(node.getValue(), env));
    }

    public static RuntimeValue evaluateObjectExpression(ObjectLiteral object, Environment env) {
        ObjectValue runtimeObject = new ObjectValue(new HashMap<>());

        for (Property property : object.getProperties()) {
            String key = property.getKey();
            Expression value = property.getValue();
            RuntimeValue runtimeValue = value == null ? env.lookupVariable(key) : Interpreter.evaluate(value, env);

            runtimeObject.getValue().put(key, runtimeValue);
        }

        return runtimeObject;
    }

    public static RuntimeValue evaluateCallExpression(CallExpr expression, Environment env) {
        List<RuntimeValue> args = new ArrayList<>();
        for (Expression arg : expression.getArgs()) {
            args.add(Interpreter.evaluate(arg, env));
        }

        RuntimeValue func = Interpreter.evaluate(expression.getCaller(), env);

        if (func.getType().equals(RuntimeValue.ValueTypes.NATIVE_FUNCTION)) {
            return ((NativeFunctionValue) func).call(args, env);
        }

        if (func.getType().equals(RuntimeValue.ValueTypes.FUNCTION)) {
            FunctionValue fn = (FunctionValue) func;
            Environment scope = new Environment(fn.getDeclarationEnv());

            for (int i = 0; i < fn.getParameters().size(); i++) {
                String varName = fn.getParameters().get(i);
                scope.declareVariable(varName, args.get(i), false);
            }

            RuntimeValue result = new NullValue();
            for (Statement statement : fn.getBody()) {
                result = Interpreter.evaluate(statement, scope);
            }

            return result;
        }

        throw new RuntimeException("Trying to call a non-function value as a function: " + func);
    }
}