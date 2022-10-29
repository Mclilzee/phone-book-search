package searchable;

import java.io.File;
import java.time.Duration;

public class JumpSearcher extends Searcher {

    public JumpSearcher(File toFind, File searchableFile) {
        super(toFind, searchableFile);
    }

    @Override
    String search() {
        return null;
    }

    @Override
    boolean isElementInSearchableFile(Record element) {
        return false;
    }

    @Override
    int numberOfElementsFound() {
        return 0;
    }
}
