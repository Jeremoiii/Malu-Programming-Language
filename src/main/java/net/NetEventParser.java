package net;

/*
* Diese Methode soll dabei helfen, die Events der Netzwerkkommunikation zu parsen. Hierfür wird ein Eventname und ein Inhalt voneinander getrennt.
*
* Wenn man also beispielsweise den String "[__ctx:net:login] rest des String" an den Client / Server sendet, dann soll der Eventname "login" und der Inhalt "rest des String" sein.
* So kann man bestens die Events voneinander unterscheiden und den Inhalt entsprechend verarbeiten.
*/
public class NetEventParser {
    public static NetEvent parseString(String input) {
        int eventStart = input.indexOf(":") + 1;
        int eventEnd = input.indexOf("]", eventStart);
        if (eventStart == -1 || eventEnd == -1 || eventEnd <= eventStart) {
            System.out.println("Ungültiges Eingabeformat.");
            return null;
        }
        String eventName = input.substring(eventStart, eventEnd);

        int contentStart = eventEnd + 1;
        if (contentStart >= input.length()) {
            System.out.println("Ungültiges Eingabeformat.");
            return null;
        }
        String content = input.substring(contentStart).trim();

        return new NetEvent(eventName, content);
    }

    public static void main(String[] args) {
        String input = "[__ctx:net:login] rest des String";
        NetEvent event = parseString(input);
        if (event != null) {
            System.out.println("EventName: " + event.getEventName());
            System.out.println("EventContent: " + event.getContent());
        }
    }
}
