package searchable;

import java.io.File;
import java.time.Duration;

public class JumpSearcher extends Searcher {

    public JumpSearcher(File toFind, File searchableFile) {
        super(toFind, searchableFile);
        RecordSorter.bubbleSort(super.searchableFile);
    }

    @Override
    String search() {
        return "";
    }

    @Override
    boolean isElementInSearchableFile(Record element) {
        int current = 0;
        int previous = 0;
        int skip = (int) Math.sqrt(super.searchableFile.length);
        int last = super.searchableFile.length - 1;

        while (element.compareTo(super.searchableFile[current]) > 0) {
            if (current == last) {
                return false;
            }

            previous = current;
            current = Math.min(last, current + skip);
        }

        while (element.compareTo(super.searchableFile[current]) < 0) {
            current--;

            if (current == previous) {
                return false;
            }
        }

        return element.equals(super.searchableFile[current]);
    }
}
