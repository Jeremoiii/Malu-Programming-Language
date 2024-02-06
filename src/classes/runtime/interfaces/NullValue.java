package classes.runtime.interfaces;

import classes.runtime.types.ValueTypes;

public class NullValue implements RuntimeValue {
    private final ValueTypes type = ValueTypes.NULL;
    private final String value = "null";

    @Override
    public ValueTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}