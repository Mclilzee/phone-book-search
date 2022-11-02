package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public class BubbleSorter<T extends Comparable<T>> extends ContactSorter<T> {

    private final Duration maxDuration = Duration.ZERO;

    public BubbleSorter(Duration duration) {
        super(duration);
    }

    public T[] getSorted(T[] array) {
        T[] copyArray = Arrays.copyOf(array, array.length);

        Duration start = Duration.ofMillis(System.currentTimeMillis());
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

                if (Duration.ofMillis(System.currentTimeMillis()).minus(start).compareTo(maxDuration) > 0) {
                    setCurrentDuration(start, maxDuration);
                    throw new RuntimeException();
                }
            }
        }

        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setCurrentDuration(start, end);
        return copyArray;
    }

    @Override
    public ContactSorter<T> withMaxDuration(Duration maxDuration) {
        return new BubbleSorter<>(maxDuration);
    }
}
