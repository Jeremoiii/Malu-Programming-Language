package utils;

// Einfache Klasse, die einen String in CamelCase umwandelt.
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

    // Lokaler Test um zu demonstrieren, was die Methode macht.
    public static void main(String[] args) {
        String input = "hello world";
        String camelCase = convertToCamelCase(input);
        System.out.println("CamelCase von '" + input + "': " + camelCase);
    }
}
