package searchable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Searcher {

    Record[] searchableFile;
    Record[] toFind;

    Searcher(File toFind, File searchableFile, boolean sorted) {
        this.searchableFile = readFileToRecordArray(searchableFile);
        this.toFind = readFileToRecordArray(toFind);

        if (sorted) {
            Arrays.sort(this.searchableFile);
            Arrays.sort(this.toFind);
        }
    }

    Searcher(File toFind, File searchableFile) {
        this(toFind, searchableFile, false);
    }

    public String search() {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        System.out.println(getSearchingMessage());

        int found = foundSubArrayElements();
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());
        return getFormattedMessage(startDuration, endDuration, found);
    }

    Record[] readFileToRecordArray(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(this::getRecordFromString)
                    .toArray(Record[]::new);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new Record[0];
    }

    String getFormattedMessage(Duration start, Duration end, int found) {
        Duration duration = end.minus(start);

        return String.format("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                found, this.toFind.length, duration.toMinutes(), duration.toSecondsPart(), duration.toMillisPart());
    }

    abstract boolean isElementInSearchableFile(Record element);

    abstract String getSearchingMessage();

    abstract int foundSubArrayElements();

    Record getRecordFromString(String string) {
        String[] values = string.split(" ");
        if (values[0].matches("\\d+")) {
            return new Record(values[0], extractName(1, values));
        } else {
            return new Record(extractName(0, values));
        }
    }

    private String extractName(int skip, String[] string) {
        return Arrays.stream(string)
                .skip(skip)
                .collect(Collectors.joining(" "));
    }
}
