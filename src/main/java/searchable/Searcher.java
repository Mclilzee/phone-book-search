package searchable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public abstract class Searcher {

    String[] searchableFile;
    String[] toFind;

    Searcher(File toFind, File searchableFile) {
        this.searchableFile = readFileIntoStringArray(searchableFile);
        this.toFind = readFileIntoStringArray(toFind);
    }

    public String search() {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        System.out.println("Start searching...");

        int found = foundSubArrayElements();
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());
        return getFormattedMessage(startDuration, endDuration, found);
    }

    String[] readFileIntoStringArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new String[0];
    }

    String getFormattedMessage(Duration start, Duration end, int found) {
        Duration duration = end.minus(start);

        return String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, this.toFind.length, duration.toMinutes(), duration.toSecondsPart(), duration.toMillisPart());
    }

    abstract int foundSubArrayElements();

    abstract boolean isElementInSearchableFile(String element);
}
