package utils.JSON;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JSONStringModifier {
    public static String removeOwnerPart(String jsonString) {
        // Entfernen des "owner"-Teils samt Inhalt
        int startIndex = jsonString.indexOf("\"owner\"");
        if (startIndex != -1) {
            int endIndex = jsonString.indexOf("\"", startIndex + 9); // Findet das nächste Anführungszeichen nach "owner":"
            if (endIndex != -1) {
                jsonString = jsonString.substring(0, startIndex) + jsonString.substring(endIndex + 1);
            }
        }

        // Entfernen möglicher überflüssiger Kommas und Doppelpunkte
        jsonString = jsonString.replaceAll(",\\s*,", ",");
        jsonString = jsonString.replaceAll(",\\s*}", "}");
        jsonString = jsonString.replaceAll(",\\s*]", "]");

        return jsonString;
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"asd.malu\",\"content\":\"s2UoU0cxBKCHbgZvgl8X9g\\u003d\\u003d\",\"owner\":\"{username\\u003dasd\",\"password\":\"f10e2821bbbea527ea02200352313bc059445190\",\"id\":\"0.0\"}";
        String updatedJsonString = removeOwnerPart(jsonString);
        System.out.println(updatedJsonString);
    }
}

