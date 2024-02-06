package classes.runtime.interfaces;

import classes.runtime.types.ValueTypes;

public class NumberValue implements RuntimeValue {
    private final ValueTypes type = ValueTypes.NUMBER;
    private final double value;

    public NumberValue(double value) {
        this.value = value;
    }

    @Override
    public ValueTypes getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}