import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    public static boolean isElementInArray(String element, String[] strings) {
        if (strings == null || element == null) {
            return false;
        }

        for (String string : strings) {
            if (Objects.equals(element, string)) {
                return true;
            }
        }

        return false;
    }

    public static int countSubArrayElements(String[] subArray, String[] array) {
        if (subArray == null || array == null) {
            return 0;
        }

        int count = 0;
        for (String element : subArray) {
            if (isElementInArray(element, array)) {
                count++;
            }
        }

        return count;
    }

    public static String[] readFileIntoStringArray(String filename) {
        return new String[]{"Mark zergberg", "John Doe", "Gly marksman"};
    }
}
