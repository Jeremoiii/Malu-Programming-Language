package runtime;

import buffer.StringBuffer;
import runtime.values.*;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import utils.ObjectPrinter;

public class Environment {
    private final Environment parent;
    private final Map<String, RuntimeValue> variables;
    private final Set<String> constants;

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
            StringBuffer.getInstance().append("Cannot reassign constant " + varName);
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

    public static Environment createGlobalEnvironment(boolean... print) {
        Environment env = new Environment(null);

        // Variables
        env.declareVariable("true", new BooleanValue(true), true);
        env.declareVariable("false", new BooleanValue(false), true);
        env.declareVariable("null", new NullValue(), true);

        // Native Functions
        env.declareVariable("print", new NativeFunctionValue((args, scope) -> {
            StringBuilder returnValue = new StringBuilder();

            args.stream()
                    .map(arg -> arg.getValue() != null ? arg.getValue().toString() : "[" + arg.getType() + "]")
                    .forEach(arg -> {
                        if (print.length > 0) {
                            System.out.println(arg);
                        }
                        StringBuffer.getInstance().append(arg);
                    });
            return new NullValue();
        }), true);

        env.declareVariable("log", new NativeFunctionValue((args, scope) -> {
            if (print.length > 0) {
                System.out.println(ObjectPrinter.deserializeObjectToString(args));
            }
            StringBuffer.getInstance().append(ObjectPrinter.deserializeObjectToString(args));
            return new NullValue();
        }), true);

        env.declareVariable("malu", new NativeFunctionValue((args, scope) -> {
            for (long i = 0; i <= 1e10; i++) {
                System.out.println("I Love You a " + i + " times ♥!\r");
            }
            return new NullValue();
        }), true);

        env.declareVariable("getArg", new NativeFunctionValue((args, scope) -> {
           if (args.size() != 1) {
               throw new RuntimeException("isEven function expects 1 argument");
           }

           System.out.println("args: " + args.get(0).getValue());

           return new NullValue();
        }), true);

        FunctionCall timeFunction = (_args, _scope) -> new NumberValue(System.currentTimeMillis());

        env.declareVariable("time", new NativeFunctionValue(timeFunction), true);

        return env;
    }
}