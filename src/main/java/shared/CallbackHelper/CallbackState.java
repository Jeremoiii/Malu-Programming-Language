package shared.CallbackHelper;

public class CallbackState {
    private static CallbackState instance = null;

    private String name;
    private boolean processed;
    private String response;

    private CallbackState(String name) {
        this.name = name;
        this.processed = false;
    }

    public static CallbackState register(String name) {
        instance = new CallbackState(name);
        return instance;
    }

    public static CallbackState getInstance(String name) {
        if (instance == null) {
            instance = new CallbackState(name);
        }
        return instance;
    }

    public void setProcessed() {
        this.processed = true;
    }

    public boolean isProcessed() {
        return this.processed;
    }

    public String getName() {
        return this.name;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
