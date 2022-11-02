package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public class BubbleSorter<T extends Comparable<T>> extends ContactSorter {

    private Duration maxDuration = Duration.ZERO;

    public BubbleSorter(Duration duration) {
        super(duration);
    }
    public Contact[] getSorted(Contact[] array) {
        Contact[] copyArray = Arrays.copyOf(array, array.length);

        Duration start = Duration.ofMillis(System.currentTimeMillis());
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < copyArray.length - 1; i++) {
                Contact previous = copyArray[i];
                Contact next = copyArray[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    copyArray[i] = next;
                    copyArray[i + 1] = previous;
                    quit = false;
                }

                if (Duration.ofMillis(System.currentTimeMillis()).minus(start).compareTo(maxDuration) > 0) {
                    throw new RuntimeException();
                }
            }
        }

        return copyArray;
    }

    public void setMaxDuration(Duration duration) {
        this.maxDuration = duration;
    }

    public BubbleSorter withMaxDuration(Duration maxDuration) {
        return new BubbleSorter(maxDuration);
    }
}
