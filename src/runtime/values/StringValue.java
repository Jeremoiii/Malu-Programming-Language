package runtime.values;

import runtime.RuntimeValue;


public class StringValue extends RuntimeValue {

    private String value;

    public StringValue(String value) {
        setType(ValueTypes.STRING);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}