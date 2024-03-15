package utils.JSON;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
* Aufgrund dessen, dass die Klasse Projects eine List mit dem Inhalt ProjectFile besitzt, wird die Klasse ProjectFile nicht als JSON-Objekt erkannt.
* Dementsprechend, muss ich den String zunächst einmal als JSON-Objekt konvertieren um es anschließend zu einem Objekt zurückverwandeln zu können.
* der jsonString ist hier das JSON-Objekt. jedoch muss ich noch owner entfernen, da es nicht weiterhin relevant ist und die Rückverwandlung
* zu einem Objekt (ProjectFile) verhindert.
*/
public class JSONStringModifier {
    public static String removeOwnerPart(String jsonString) {
        int startIndex = jsonString.indexOf("\"owner\"");
        if (startIndex != -1) {
            int endIndex = jsonString.indexOf("\"", startIndex + 9);
            if (endIndex != -1) {
                jsonString = jsonString.substring(0, startIndex) + jsonString.substring(endIndex + 1);
            }
        }

        jsonString = jsonString.replaceAll(",\\s*,", ",");
        jsonString = jsonString.replaceAll(",\\s*}", "}");
        jsonString = jsonString.replaceAll(",\\s*]", "]");

        return jsonString;
    }


    // Lokaler Test um zu demonstrieren, was die Methode macht.
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"asd.malu\",\"content\":\"s2UoU0cxBKCHbgZvgl8X9g\\u003d\\u003d\",\"owner\":\"{username\\u003dasd\",\"password\":\"f10e2821bbbea527ea02200352313bc059445190\",\"id\":\"0.0\"}";
        String updatedJsonString = removeOwnerPart(jsonString);
        System.out.println(updatedJsonString);
    }
}

