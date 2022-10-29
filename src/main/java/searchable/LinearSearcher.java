package searchable;

import java.io.File;
import java.time.Duration;

public class LinearSearcher extends Searcher {

    public LinearSearcher(File toFind, File searchableFile) {
        super(toFind, searchableFile);
    }

    @Override
    public String search() {
        Duration startDuration = Duration.ofMillis(System.currentTimeMillis());
        int found = numberOfElementsFound();
        Duration endDuration = Duration.ofMillis(System.currentTimeMillis());

        return String.format("Start searching (linear search)...\n%s", getFoundMessage(startDuration, endDuration, found));
    }

    @Override
    int numberOfElementsFound() {
        int count = 0;
        for (Record element : super.toFind) {
            if (isElementInSearchableFile(element)) {
                count++;
            }
        }

        return count;
    }

    @Override
    boolean isElementInSearchableFile(Record element) {
        for (Record record : super.searchableFile) {
            if (record.equals(element)) {
                return true;
            }
        }

        return false;
    }
}
