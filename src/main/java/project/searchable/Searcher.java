package project.searchable;

import java.time.Duration;
import java.util.Arrays;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;

abstract class Searcher<T extends Comparable<T>> {

    T[] searchableContent;
    final T[] toFind;
    final Sorter<T> sorter;
    Duration searchDuration = Duration.ZERO;
    private int found = 0;

    Searcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
        this.searchableContent = searchableContent;
        this.toFind = toFind;
        this.sorter = sorter;
    }

    Searcher(T[] searchableContent, T[] toFind) {
        // use default bubble sorter if none specified
        this(searchableContent, toFind, new BubbleSorter<>(Duration.ofSeconds(1)));
    }

    public Duration getSearchDuration() {
        return this.searchDuration;
    }

    void setSearchDuration(Duration start, Duration end) {
        this.searchDuration = end.minus(start);
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

    String getFoundMessage() {
        return getFoundMessage(Duration.ZERO);
    }

    String getDurationString(Duration duration) {
        if (duration.isNegative()) {
            duration = Duration.ZERO;
        }
        return String.format("%d min. %d sec. %d ms.", duration.toMinutesPart(), duration.toSecondsPart(), duration.toMillisPart());
    }

    public String search() {
        String message;
        try {
            searchableContent = sorter.getSorted(searchableContent);
            this.findElements();
            message = searchingMessage(this);
        } catch (InterruptedException e) {
            LinearSearcher<T> searcher = new LinearSearcher<>(searchableContent, toFind);
            searcher.findElements();
            message = searchingMessage(searcher);
        }

        return message;
    }

    private String searchingMessage(Searcher<T> searcher) {
        StringBuilder builder = new StringBuilder();
        builder.append(searcher.getFoundMessage(sorter.getCurrentDuration())).append("\n");
        builder.append("Sorting time: ").append(getDurationString(sorter.getCurrentDuration()));
        if (searcher instanceof LinearSearcher) {
            builder.append(" - STOPPED, moved to linear search");
        }
        builder.append("\nSearching time: ").append(getDurationString(searcher.searchDuration));

        return builder.toString();
    }

    abstract boolean isElementInSearchableFile(T element);
}
