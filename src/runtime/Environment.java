package runtime;

import runtime.values.*;

import java.util.*;
import java.util.stream.Collectors;
import utils.ObjectPrinter;

public class Environment {
    private Environment parent;
    private Map<String, RuntimeValue> variables;
    private Set<String> constants;

    public Environment(Environment parentENV) {
        this.parent = parentENV;
        this.variables = new HashMap<>();
        this.constants = new HashSet<>();
    }

    public RuntimeValue declareVariable(String varName, RuntimeValue value, boolean constant) {
        if (this.variables.containsKey(varName)) {
            throw new RuntimeException("Variable " + varName + " already declared in this scope");
        }

        this.variables.put(varName, value);
        if (constant) {
            this.constants.add(varName);
        }

        return value;
    }

    public RuntimeValue assignVariable(String varName, RuntimeValue value) {
        Environment env = this.resolve(varName);

        if (env.constants.contains(varName)) {
            throw new RuntimeException("Cannot reassign constant " + varName);
        }

        env.variables.put(varName, value);

        return value;
    }

    public RuntimeValue lookupVariable(String varName) {
        Environment env = this.resolve(varName);
        return env.variables.get(varName);
    }

    public Environment resolve(String varName) {
        if (this.variables.containsKey(varName)) {
            return this;
        }

        if (this.parent == null) {
            throw new RuntimeException("Cannot resolve variable " + varName);
        }

        return this.parent.resolve(varName);
    }

    public static Environment createGlobalEnvironment() {
        Environment env = new Environment(null);

        env.declareVariable("true", new BooleanValue(true), true);
        env.declareVariable("false", new BooleanValue(false), true);
        env.declareVariable("null", new NullValue(), true);

        // Native Functions
        env.declareVariable("print", new NativeFunctionValue((args, scppe) -> {
            System.out.println(ObjectPrinter.deserializeObjectToString(args.stream().map(arg -> arg.toString()).collect(Collectors.joining(", "))));
            return new NullValue();
        }), true);

        env.declareVariable("log", new NativeFunctionValue((args, scope) -> {
            System.out.println(args);
            return new NullValue();
        }), true);

        env.declareVariable("malu", new NativeFunctionValue((args, scope) -> {
            for (long i = 0; i <= 1e10; i++) {
                System.out.println("I Love You a " + i + " times ♥!\r");
            }
            return new NullValue();
        }), true);

        FunctionCall timeFunction = (_args, _scope) -> new NumberValue(System.currentTimeMillis());

        env.declareVariable("time", new NativeFunctionValue(timeFunction), true);

        return env;
    }
}