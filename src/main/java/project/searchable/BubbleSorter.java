package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public class BubbleSorter<T extends Comparable<T>> extends ContactSorter<T> {

    private final Duration maxDuration = Duration.ZERO;

    public BubbleSorter(Duration duration) {
        super(duration);
    }

    @Override
    T[] startSorting(T[] unsortedArray) {
        T[] copyArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < copyArray.length - 1; i++) {
                T previous = copyArray[i];
                T next = copyArray[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    copyArray[i] = next;
                    copyArray[i + 1] = previous;
                    quit = false;
                }
            }
        }

        return copyArray;
    }

    public ContactSorter<T> withMaxDuration(Duration maxDuration) {
        return new BubbleSorter<>(maxDuration);
    }
}
