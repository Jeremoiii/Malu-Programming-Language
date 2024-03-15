package utils.JSON;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* Diese Methode konvertiert einen String, der wie ein Map-Objekt aussieht, in ein JSON-Objekt. Das muss gemacht werden, da die Klasse Projects eine List mit dem Inhalt ProjectFile besitzt.
* Die Daten der Klasse ProjectFile werden nicht als JSON-Objekt erkannt. Dementsprechend, muss ich den String zunächst einmal als JSON-Objekt konvertieren um es anschließend
* zu einem Objekt zurückverwandeln zu können.
*/
public class MapToJSONConverter {

    public static String convertToJSON(String mapString) {
        mapString = mapString.substring(1, mapString.length() - 1);
        mapString = mapString.replaceAll(", ", ",");

        String[] keyValuePairs = mapString.split(",");
        Map<String, Object> map = new LinkedHashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2);
            String key = entry[0].trim();
            String value = entry.length > 1 ? entry[1].trim() : "";

            map.put(key, value);
        }

        Gson gson = new Gson();
        return gson.toJson(map);
    }


    // Lokaler Test um zu demonstrieren, was die Methode macht.
    public static void main(String[] args) {
        String mapString = "{name=asd.malu, content=s2UoU0cxBKCHbgZvgl8X9g==, owner={username=asd, password=f10e2821bbbea527ea02200352313bc059445190, id=0.0}}";
        String jsonString = convertToJSON(mapString);
        System.out.println(jsonString);
    }
}
