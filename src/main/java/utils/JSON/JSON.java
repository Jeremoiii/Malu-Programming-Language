package utils.JSON;

import com.google.gson.Gson;


/*
* Diese Klasse dient dazu, die Methoden zum Konvertieren von Objekten in JSON-Strings und umgekehrt zu kapseln.
* Das ist notwendig, da man die Methoden sonst nicht über die Netzwerkklassen verschicken kann.
*/
public class JSON {
    private static final Gson GSON = new Gson();

    public static <T> String stringify(T object) {
        return GSON.toJson(object);
    }

    public static <T> T parse(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}
