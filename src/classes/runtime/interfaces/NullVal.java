package classes.runtime.interfaces;

import classes.runtime.types.RuntimeVal;
import classes.runtime.values;

/**
 * Defines a value of undefined meaning
 */
public class NullVal implements values {
    private final RuntimeVal type = RuntimeVal.NULL;
    private final String value = "null";

    @Override
    public RuntimeVal getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
