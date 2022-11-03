package project.searchable;

import java.time.Duration;

public class BubbleSorter<T extends Comparable<T>> extends Sorter<T> {

    public BubbleSorter(Duration maxDuration) {
        super(maxDuration);
    }

    @Override
    void startSorting(T[] unsortedArray) {
        boolean quit = false;
        while (!quit) {
            // if no operation made, the array is sorted quit safely.
            quit = true;

            for (int i = 0; i < unsortedArray.length - 1; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                T previous = unsortedArray[i];
                T next = unsortedArray[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    // switch elements
                    unsortedArray[i] = next;
                    unsortedArray[i + 1] = previous;
                    quit = false;
                }
            }
        }
    }
}
