package runtime.values;

import runtime.Environment;
import runtime.RuntimeValue;

import java.util.List;

public class NativeFunctionValue extends RuntimeValue {
    private FunctionCall call;

    public NativeFunctionValue(FunctionCall call) {
        setType(ValueTypes.NATIVE_FUNCTION);
        this.call = call;
    }

    public FunctionCall getCall() {
        return call;
    }

    public void setCall(FunctionCall call) {
        this.call = call;
    }

    public RuntimeValue call(List<RuntimeValue> args, Environment env) {
        return call.call(args, env);
    }
}
