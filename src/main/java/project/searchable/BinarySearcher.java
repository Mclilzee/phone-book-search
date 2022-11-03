package project.searchable;

public class BinarySearcher<T extends Comparable<T>> extends Searcher<T> {

    public BinarySearcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
        super(searchableContent, toFind, sorter);
    }

    public BinarySearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    boolean isElementInSearchableFile(T element) {
        return false;
    }
}
