package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public class BubbleSorter<T extends Comparable<T>> extends ContactSorter<T> {

    public BubbleSorter(Duration maxDuration) {
        super(maxDuration);
    }

    @Override
    public T[] getSorted(T[] unsortedArray) {
        T[] copyArray = Arrays.copyOf(unsortedArray, unsortedArray.length);

        Duration start = Duration.ofMillis(System.currentTimeMillis());
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < copyArray.length - 1; i++) {
                if (Duration.ofMillis(System.currentTimeMillis()).minus(start).compareTo(getMaxDuration()) >= 0) {
                    setCurrentDurationToMax();
                    throw new RuntimeException();
                }
                T previous = copyArray[i];
                T next = copyArray[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    copyArray[i] = next;
                    copyArray[i + 1] = previous;
                    quit = false;
                }

                checkTimeLimit(start);
            }
        }

        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setCurrentDuration(start, end);
        return copyArray;
    }

    private void checkTimeLimit(Duration start) {
        Duration current = Duration.ofMillis(System.currentTimeMillis()).minus(start);
        if (current.compareTo(getMaxDuration()) >= 0) {
            setCurrentDurationToMax();
            throw new RuntimeException();
        }
    }

    public ContactSorter<T> withMaxDuration(Duration maxDuration) {
        return new BubbleSorter<>(maxDuration);
    }
}
