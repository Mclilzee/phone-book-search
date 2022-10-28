import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public abstract class Searcher {

    private String[] searchableDirectory;
    private String[] toFind;

    protected Searcher(File toFind, File searchableDirectory) {
        this.searchableDirectory = readFileIntoStringArray(searchableDirectory);
        this.toFind = readFileIntoStringArray(toFind);
    }

    public abstract String search();

    protected abstract int foundSubArrayElements();
    protected abstract boolean isElementInArray();

    protected static String[] readFileIntoStringArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new String[0];
    }

    protected static String getFormattedMessage(Duration start, Duration end, int found, int total) {
        Duration duration = end.minus(start);

        return String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, total, duration.toMinutes(), duration.toSecondsPart(), duration.toMillisPart());
    }
}
