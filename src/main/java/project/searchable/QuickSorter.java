package project.searchable;

import java.time.Duration;

public class QuickSorter<T extends Comparable<T>> extends Sorter<T> {

    public QuickSorter(Duration duration) {
        super(duration);
    }

    @Override
    void startSorting(T[] unsortedArray) {

    }
}
