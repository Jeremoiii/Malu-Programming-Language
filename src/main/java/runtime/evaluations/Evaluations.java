package runtime.evaluations;

import frontend.AST.Expression;
import frontend.AST.Program;
import frontend.AST.Statement;
import frontend.AST.declarations.FunctionDeclaration;
import frontend.AST.declarations.VariableDeclaration;
import runtime.Environment;
import runtime.RuntimeValue;
import runtime.values.FunctionValue;
import runtime.values.NullValue;
import runtime.Interpreter;

import java.util.*;

public class Evaluations {
    public static RuntimeValue evaluateProgram(Program program, Environment env) {
        RuntimeValue lastEvaluated = new NullValue();

        for (Statement statement : program.getBody()) {
            lastEvaluated = Interpreter.evaluate(statement, env);
        }

        return lastEvaluated;
    }

    public static RuntimeValue evaluateVariableDeclaration(VariableDeclaration declaration, Environment env) {
        RuntimeValue value = declaration.getValue() != null ? Interpreter.evaluate(declaration.getValue(), env) : new NullValue();
        return env.declareVariable(declaration.getIdentifier(), value, declaration.isConstant());
    }

    public static RuntimeValue evaluateFunctionDeclaration(FunctionDeclaration declaration, Environment env) {
        FunctionValue func = new FunctionValue(
                declaration.getName(),
                declaration.getParameters(),
                env,
                (List<Statement>) declaration.getBody()
        );

        return env.declareVariable(declaration.getName(), func, true);
    }
}