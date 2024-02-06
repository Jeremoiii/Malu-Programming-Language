package classes.runtime.interfaces;

import classes.runtime.types.ValueTypes;

public class NullValue implements RuntimeValue {
    private final ValueTypes type = ValueTypes.NULL;

    @Override
    public ValueTypes getType() {
        return type;
    }

    public String getValue() {
        return "null";
    }
}
