package runtime.values;

import runtime.RuntimeValue;

public class NullValue extends RuntimeValue {

    private final Object value = null;

    public NullValue() {
        setType(ValueTypes.NULL);
    }

    public Object getValue() {
        return value;
    }
}