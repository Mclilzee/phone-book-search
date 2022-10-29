package searchable;

import java.io.File;

public class LinearSearcher extends Searcher {

    public LinearSearcher(File toFind, File searchableFile) {
        super(toFind, searchableFile);
    }

    @Override
    int foundSubArrayElements() {
        int count = 0;
        for (String element : super.toFind) {
            if (isElementInSearchableFile(element)) {
                count++;
            }
        }

        return count;
    }

    @Override
    boolean isElementInSearchableFile(String element) {
        for (String string : super.searchableFile) {
            if (string.contains(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    String getSearchingMessage() {
        return "Start searching (linear search)...";
    }
}
