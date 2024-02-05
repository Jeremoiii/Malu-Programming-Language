package classes.utils;

public class Shift {
    public static char[] shift(char[] array) {
        char[] newArray = new char[array.length - 1];
        System.arraycopy(array, 1, newArray, 0, newArray.length);
        return newArray;
    }
}
