package project.searchable;

import java.time.Duration;

public class LinearSearcher<T extends Comparable<T>> extends Searcher<T> {

    public LinearSearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    public String search() {
        findElements();
        return getFoundMessage(Duration.ZERO);
    }

    @Override
    boolean isElementInSearchableFile(T element) {
        for (T each : this.searchableContent) {
            if (each.equals(element)) {
                return true;
            }
        }

        return false;
    }
}
