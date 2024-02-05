package classes.runtime.interfaces;

import classes.runtime.types.RuntimeVal;
import classes.runtime.values;

/**
 * Runtime value that has access to the raw native java number.
 */
public class NumberVal implements values {
    private final RuntimeVal type = RuntimeVal.NUMBER;
    private final double value;

    public NumberVal(double value) {
        this.value = value;
    }

    @Override
    public RuntimeVal getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}
