package utils.JSON;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapToJSONConverter {

    public static String convertToJSON(String mapString) {
        // Entferne Klammern am Anfang und am Ende des Strings
        mapString = mapString.substring(1, mapString.length() - 1);

        // Ersetze alle Kommas durch ein Komma und Leerzeichen
        mapString = mapString.replaceAll(", ", ",");

        // Aufteilen des Strings in einzelne Schlüssel-Wert-Paare
        String[] keyValuePairs = mapString.split(",");

        // Erstellen einer Map für die Konvertierung
        Map<String, Object> map = new LinkedHashMap<>();

        // Durchlaufe alle Schlüssel-Wert-Paare und füge sie der Map hinzu
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2); // max. 2 Teile: Schlüssel und Wert
            String key = entry[0].trim();
            String value = entry.length > 1 ? entry[1].trim() : ""; // Wert kann leer sein

            // Füge das Schlüssel-Wert-Paar der Map hinzu
            map.put(key, value);
        }

        // Erstellen eines Gson-Objekts zur Konvertierung in JSON
        Gson gson = new Gson();

        // Konvertieren der Map in einen JSON-String und Rückgabe
        return gson.toJson(map);
    }

    public static void main(String[] args) {
        String mapString = "{name=asd.malu, content=s2UoU0cxBKCHbgZvgl8X9g==, owner={username=asd, password=f10e2821bbbea527ea02200352313bc059445190, id=0.0}}";
        String jsonString = convertToJSON(mapString);
        System.out.println(jsonString);
    }
}
