package net;

public class NetEvent {
    String eventName;
    String content;

    public NetEvent(String eventName, String content) {
        this.eventName = eventName;
        this.content = content;
    }

    public String getEventName() {
        return eventName;
    }

    public String getContent() {
        return content;
    }
}
