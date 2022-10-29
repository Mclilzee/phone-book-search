package project.searchable;

import java.time.Duration;
import java.util.Arrays;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;

abstract class Searcher {

    Record[] searchableRecords;
    Record[] toFind;

    Searcher(Record[] searchableRecords, Record[] toFind) {
        this.searchableRecords = searchableRecords;
        this.toFind = toFind;
    }

    int numberOfElementsFound() {
        return Arrays.stream(toFind)
                .filter(this::isElementInSearchableFile)
                .collect(collectingAndThen(counting(), Long::intValue));
    }

    String getFoundMessage(Duration start, Duration end, int found) {
        String durationString = getDurationString(end.minus(start));

        return String.format("Found %d / %d entries. Time taken: %s",
                found, this.toFind.length, durationString);
    }

    String getDurationString(Duration duration) {
        return String.format("%d min. %d sec. %d ms.", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

    abstract String search();

    abstract boolean isElementInSearchableFile(Record element);
}
