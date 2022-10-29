package project.searchable;

import java.time.Duration;

class ContactSorter {

    static void bubbleSort(Contact[] array, Duration maxDuration) {
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
}
