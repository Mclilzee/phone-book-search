package project.searchable;

import java.time.Duration;

public class LinearSearcher<T extends Comparable<T>> extends Searcher<T> {

    public LinearSearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    public String search() {
        findElements();
        return String.format("Start searching (linear search)...\n%s", getFoundMessage(Duration.ZERO));
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
