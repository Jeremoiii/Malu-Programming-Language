package runtime;

public abstract class RuntimeValue {
    public enum ValueTypes {
        NULL, NUMBER, BOOLEAN, OBJECT, NATIVE_FUNCTION, FUNCTION
    }

    private ValueTypes type;

    public ValueTypes getType() {
        return type;
    }

    public void setType(ValueTypes type) {
        this.type = type;
    }
}
