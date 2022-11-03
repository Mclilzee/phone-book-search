package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public class QuickSorter<T extends Comparable<T>> extends Sorter<T> {

    public QuickSorter(Duration duration) {
        super(duration);
    }

    @Override
    void startSorting(T[] unsortedArray) {
    }
}
