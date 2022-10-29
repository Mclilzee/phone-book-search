package searchable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

abstract class Searcher {

    Record[] searchableFile;
    Record[] toFind;

    Searcher(File toFind, File searchableFile) {
        this.searchableFile = readFileToRecordArray(searchableFile);
        this.toFind = readFileToRecordArray(toFind);
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

    String getFormattedMessage(Duration start, Duration end, int found) {
        String durationString = getDurationString(start, end);

        return String.format("Found %d / %d entries. Time taken: %s",
                found, this.toFind.length, durationString);
    }

    String getDurationString(Duration start, Duration end) {
        Duration duration = end.minus(start);
        return String.format("%d min. %d sec. %d ms.", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

    abstract String search();

    abstract boolean isElementInSearchableFile(Record element);

    abstract int numberOfElementsFound();
}
