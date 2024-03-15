package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Diese Klasse ist verantwortlich für das Deserialisieren von Java-Objekten in JSON-Strings.
// So kann man besser sehen, welche Daten in den Objekten enthalten sind.
public final class ObjectPrinter {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private ObjectPrinter() {
        throw new UnsupportedOperationException("Instantiation of this class is not permitted in case you are using reflection.");
    }

    /**
     * Diese Methode ist verantwortlich für das Deserialisieren des Java-Objekts in einen JSON-String.
     *
     * @param object Das zu deserialisierende Objekt.
     * @return String
     */
    public static String deserializeObjectToString(final Object object) {
        return GSON.toJson(object);
    }
}