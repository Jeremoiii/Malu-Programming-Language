package runtime.values;

import runtime.RuntimeValue;

public class BooleanValue extends RuntimeValue {

    private boolean value;

    public BooleanValue(boolean value) {
        setType(ValueTypes.BOOLEAN);
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}