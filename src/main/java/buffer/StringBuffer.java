package buffer;

public class StringBuffer {
    private static StringBuffer instance;

    private final StringBuilder buffer;

    public StringBuffer() {
        instance = this;
        buffer = new StringBuilder();
    }

    public static StringBuffer getInstance() {
        return instance;
    }

    public StringBuilder getBuffer() {
        return buffer;
    }

    public void append(String str) {
        buffer.append(str).append("\n");
    }
}
