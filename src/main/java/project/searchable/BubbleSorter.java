package project.searchable;

import java.time.Duration;

public class BubbleSorter<T extends Comparable<T>> extends ContactSorter<T> {

    public BubbleSorter(Duration maxDuration) {
        super(maxDuration);
    }

    @Override
    void startSorting(T[] unsortedArray) {

        Duration start = Duration.ofMillis(System.currentTimeMillis());
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < unsortedArray.length - 1; i++) {
                T previous = unsortedArray[i];
                T next = unsortedArray[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    unsortedArray[i] = next;
                    unsortedArray[i + 1] = previous;
                    quit = false;
                }

            }
        }

        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setCurrentDuration(start, end);
    }

    public ContactSorter<T> withMaxDuration(Duration maxDuration) {
        return new BubbleSorter<>(maxDuration);
    }
}
