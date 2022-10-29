package project.searchable;

import java.time.Duration;

public class LinearSearcher extends Searcher {

    public LinearSearcher(Record[] searchableRecords, Record[] toFind) {
        super(searchableRecords, toFind);
    }

    @Override
    public String search() {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        findElements();
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());

        super.setSearchDuration(startDuration, endDuration);
        return String.format("Start searching (linear search)...\n%s", getFoundMessage());
    }

    @Override
    boolean isElementInSearchableFile(Record element) {
        for (Record record : super.searchableRecords) {
            if (record.equals(element)) {
                return true;
            }
        }

        return false;
    }
}
