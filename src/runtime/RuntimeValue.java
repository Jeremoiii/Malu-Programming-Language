package runtime;

public abstract class RuntimeValue {

    public enum ValueTypes {
        NULL, NUMBER, BOOLEAN, OBJECT, NATIVE_FUNCTION, FUNCTION, STRING
    }

    private ValueTypes type;

    public ValueTypes getType() {
        return type;
    }

    public void setType(ValueTypes type) {
        this.type = type;
    }

    public abstract Object getValue();
}