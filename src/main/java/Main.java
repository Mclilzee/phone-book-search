import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        String[] phoneBook = readFileIntoStringArray("./directory.txt");
        String[] toFind = readFileIntoStringArray("./find.txt");

        System.out.println("Start searching...");
        int found = foundSubArrayElements(toFind, phoneBook);
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());
        System.out.println(getFormattedMessage(startDuration, endDuration, found, toFind.length));


    }

    public static int foundSubArrayElements() {
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

    public static boolean isElementInArray(String element, String[] strings) {
        if (strings == null || element == null) {
            return false;
        }

        for (String string : strings) {
            if (string.contains(element)) {
                return true;
            }
        }

        return false;
    }

    public static String[] readFileIntoStringArray(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new String[0];
    }

    public static String getFormattedMessage(Duration start, Duration end, int found, int total) {
        Duration duration = end.minus(start);

        return String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, total, duration.toMinutes(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
