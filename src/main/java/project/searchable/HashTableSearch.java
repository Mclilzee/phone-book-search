package project.searchable;

import java.util.HashMap;
import java.util.Map;

public class HashTableSearch<T extends Comparable<T>> extends Searcher<T> {

    private Map<String, T> hashMap;

    public HashTableSearch(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
        hashMap = new HashMap<>();
    }

    @Override
    boolean isElementInSearchableFile(T element) {
        return false;
    }
}
