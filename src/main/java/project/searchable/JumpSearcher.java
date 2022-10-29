package project.searchable;

import java.time.Duration;

public class JumpSearcher extends Searcher {

    private boolean sortInterrupted = false;
    private final Duration sortingLimit;

    public JumpSearcher(Record[] searchableRecords, Record[] toFind, Duration maxSortLimit) {
        super(searchableRecords, toFind);
        this.sortingLimit = maxSortLimit;
    }

    @Override
    String search() {
        bubbleSortData();
        String message;
        if (!sortInterrupted) {
            Duration searchingStart = Duration.ofMillis(System.currentTimeMillis());
            super.findElements();
            Duration searchingEnd = Duration.ofMillis(System.currentTimeMillis());
            setSearchDuration(searchingStart, searchingEnd);
            message = searchingMessage(this);
        } else {
            LinearSearcher searcher = new LinearSearcher(searchableRecords, toFind);
            searcher.findElements();
            message = searchingMessage(searcher);
        }

        return message;
    }

    String searchingMessage(Searcher searcher) {
        StringBuilder builder = new StringBuilder("Start searching (bubble sort + jump search)...\n");
        builder.append(searcher.getFoundMessage()).append("\n");
        builder.append("Sorting time: ").append(getDurationString(sortingDuration));
        if (searcher instanceof LinearSearcher) {
            builder.append(" - STOPPED, moved to linear search");
        }
        builder.append("\nSearching time: ").append(getDurationString(searchDuration));

        return builder.toString();
    }

    private void bubbleSortData() {
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        try {
            RecordSorter.bubbleSort(super.searchableRecords, this.sortingLimit.multipliedBy(10));
        } catch (RuntimeException e) {
            sortInterrupted = true;
        } finally {
            Duration end = Duration.ofMillis(System.currentTimeMillis());
            setSortingDuration(start, end);
        }
    }

    @Override
    boolean isElementInSearchableFile(Record element) {
        int current = 0;
        int previous = 0;
        int skip = (int) Math.sqrt(super.searchableRecords.length);
        int last = super.searchableRecords.length - 1;

        while (element.compareTo(super.searchableRecords[current]) > 0) {
            if (current == last) {
                return false;
            }

            previous = current;
            current = Math.min(last, current + skip);
        }

        while (element.compareTo(super.searchableRecords[current]) < 0) {
            current--;

            if (current == previous) {
                return false;
            }
        }

        return element.equals(super.searchableRecords[current]);
    }
}
