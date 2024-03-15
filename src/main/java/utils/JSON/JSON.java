package utils.JSON;

import com.google.gson.Gson;

public class JSON {
    private static final Gson GSON = new Gson();

    public static <T> String stringify(T object) {
        return GSON.toJson(object);
    }

    public static <T> T parse(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}
