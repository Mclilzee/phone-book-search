import java.io.File;

public class LinearSearcher extends Searcher{

    public LinearSearcher(File toFind, File searchableFile) {
        super(toFind, searchableFile);
    }

    @Override
    protected int foundSubArrayElements() {
        int count = 0;
        for (String element : super.toFind) {
            if (isElementInSearchableFile(element)) {
                count++;
            }
        }

        return count;
    }

    @Override
    protected boolean isElementInSearchableFile(String element) {
        for (String string : super.searchableFile) {
            if (string.contains(element)) {
                return true;
            }
        }

        return false;
    }
}
