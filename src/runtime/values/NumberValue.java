package runtime.values;

import runtime.RuntimeValue;

public class NumberValue extends RuntimeValue {
    private double value;

    public NumberValue(double value) {
        setType(ValueTypes.NUMBER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}