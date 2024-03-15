package utils;

public class StringUtil {
    public static String convertToCamelCase(String input) {
        String[] words = input.split("\\s+");
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                resultBuilder.append(word);
            } else {
                resultBuilder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
            }
        }

        return resultBuilder.toString();
    }
}
