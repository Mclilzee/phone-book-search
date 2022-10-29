package project.searchable;

import java.time.Duration;

public class ContactSorter {

    private static Duration maxDuration = Duration.ZERO;

    static void bubbleSort(Contact[] array) {
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        boolean quit = false;
        while (!quit) {
            quit = true;

            for (int i = 0; i < array.length - 1; i++) {
                Contact previous = array[i];
                Contact next = array[i + 1];
                int comparingResult = previous.compareTo(next);

                if (comparingResult > 0) {
                    array[i] = next;
                    array[i + 1] = previous;
                    quit = false;
                }

                if (Duration.ofMillis(System.currentTimeMillis()).minus(start).compareTo(maxDuration) > 0) {
                    throw new RuntimeException();
                }
            }
        }
    }

    public static void setMaxDuration(Duration duration) {
        maxDuration = duration;
    }

    public static Duration getMaxDuration() {
        return maxDuration;
    }
}
