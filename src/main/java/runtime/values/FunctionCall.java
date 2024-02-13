package runtime.values;

import runtime.Environment;
import runtime.RuntimeValue;

import java.util.List;

public interface FunctionCall {
    RuntimeValue call(List<RuntimeValue> args, Environment env);
}
