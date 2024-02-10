package runtime.values;

import runtime.RuntimeValue;

import java.util.Map;

public class ObjectValue extends RuntimeValue {
    private Map<String, RuntimeValue> properties;

    public ObjectValue(Map<String, RuntimeValue> properties) {
        setType(ValueTypes.OBJECT);
        this.properties = properties;
    }

    public Map<String, RuntimeValue> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, RuntimeValue> properties) {
        this.properties = properties;
    }
}
