package project.searchable;

import java.time.Duration;

public class JumpSearcher extends Searcher {

    private Duration sortingDuration = Duration.ZERO;
    private boolean sortStopped = false;

    public JumpSearcher(Record[] searchableRecords, Record[] toFind, Duration maxSortLimit) {
        super(searchableRecords, toFind);
    }

    void setSortingDuration(Duration start, Duration end) {
        this.sortingDuration = end.minus(start);
    }

    @Override
    String search() {
        bubbleSortData();
        Duration sortingStart = Duration.ofMillis(System.currentTimeMillis());
        Duration sortingEnd = Duration.ofMillis(System.currentTimeMillis());

        Duration searchingStart = Duration.ofMillis(System.currentTimeMillis());
        int found = super.numberOfElementsFound();
        Duration searchingEnd = Duration.ofMillis(System.currentTimeMillis());

        return "";
    }

    private void bubbleSortData() {
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        try {
            RecordSorter.bubbleSort(super.searchableRecords, getSearchDuration().multipliedBy(10));
        } catch (RuntimeException e) {
            sortStopped = true;
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

    public Duration getSortingDuration() {
        return this.sortingDuration;
    }
}
