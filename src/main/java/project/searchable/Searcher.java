package project.searchable;

import java.time.Duration;
import java.util.Arrays;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;

abstract class Searcher {

    Contact[] searchableContacts;
    final Contact[] toFind;
    final Sorter<Contact> sorter;
    Duration searchDuration = Duration.ZERO;
    private int found = 0;

    Searcher(Contact[] searchableContacts, Contact[] toFind, Sorter<Contact> sorter) {
        this.searchableContacts = searchableContacts;
        this.toFind = toFind;
        this.sorter = sorter;
    }

    Searcher(Contact[] searchableContacts, Contact[] toFind) {
        // use default bubble sorter if none specified
        this(searchableContacts, toFind, new BubbleSorter<>(Duration.ofSeconds(1)));
    }

    public Duration getSearchDuration() {
        return this.searchDuration;
    }

    void setSearchDuration(Duration start, Duration end) {
        this.searchDuration = end.minus(start);
    }

    public Duration getSortingDuration() {
        return this.sorter.currentDuration;
    }

    int getFound() {
        return this.found;
    }

    void findElements() {
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        this.found = Arrays.stream(toFind)
                .filter(this::isElementInSearchableFile)
                .collect(collectingAndThen(counting(), Long::intValue));
        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setSearchDuration(start, end);
    }

    String getFoundMessage(Duration sortingDuration) {
        String durationString = getDurationString(searchDuration.plus(sortingDuration));

        return String.format("Found %d / %d entries. Time taken: %s",
                this.found, this.toFind.length, durationString);
    }

    String getDurationString(Duration duration) {
        if (duration.isNegative()) {
            duration = Duration.ZERO;
        }
        return String.format("%d min. %d sec. %d ms.", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

    abstract public String search();

    abstract boolean isElementInSearchableFile(Contact element);
}
