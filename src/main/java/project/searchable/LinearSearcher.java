package project.searchable;

import java.time.Duration;

public class LinearSearcher extends Searcher {

    public LinearSearcher(Record[] searchableRecords, Record[] toFind) {
        super(searchableRecords, toFind);
    }

    public Duration getSearchDuration() {
        return super.searchDuration;
    }

    @Override
    public String search() {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        int found = numberOfElementsFound();
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());

        searchDuration = endDuration.minus(startDuration);
        return String.format("Start searching (linear search)...\n%s", getFoundMessage(found));
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
