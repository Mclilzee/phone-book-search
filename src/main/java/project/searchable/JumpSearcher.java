package project.searchable;

import java.time.Duration;

public class JumpSearcher extends Searcher {

    public JumpSearcher(Record[] searchableRecords, Record[] toFind) {
        super(searchableRecords, toFind);
    }

    @Override
    String search() {
        LinearSearcher linearSearcher = new LinearSearcher(searchableRecords, toFind);
        Duration linerSearcherDuration = linearSearcher.getSearchDuration();

        Duration sortingStart = Duration.ofMillis(System.currentTimeMillis());
        RecordSorter.bubbleSort(super.searchableRecords, getSearchDuration().multipliedBy(10));
        Duration sortingEnd = Duration.ofMillis(System.currentTimeMillis());

        Duration searchingStart = Duration.ofMillis(System.currentTimeMillis());
        int found = super.numberOfElementsFound();
        Duration searchingEnd = Duration.ofMillis(System.currentTimeMillis());

        return "";
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
