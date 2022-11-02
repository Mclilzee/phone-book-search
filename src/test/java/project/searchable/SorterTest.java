package project.searchable;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    Contact[] contacts = new Contact[]{
            new Contact("Mark zergberg"),
            new Contact("John Doe"),
            new Contact("1234", "Gly Marksman"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Catman bro"),
            new Contact("John Do")
    };

    Contact[] expected = new Contact[]{
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Catman bro"),
            new Contact("1234", "Gly Marksman"),
            new Contact("John Do"),
            new Contact("John Doe"),
            new Contact("Mark zergberg"),
    };

    Sorter<Contact> sorter = new SpecificSorter<>(Duration.ofSeconds(10));

    @Test
    void sorterThrowErrorWhenExceedsTime() {
        sorter = sorter.withMaxDuration(Duration.ofSeconds(-1));
        assertThrows(InterruptedException.class, () -> sorter.getSorted(contacts));
    }

    @Test
    void setMaxLimitCorrectly() {
        sorter = sorter.withMaxDuration(Duration.ofDays(1));
        assertEquals(Duration.ofDays(1), sorter.getMaxDuration());
    }

    @Test
    void getSorted() throws InterruptedException {
        assertArrayEquals(sorter.getSorted(contacts), expected);
    }

    private static class SpecificSorter<T extends Comparable<T>> extends Sorter<T> {

        public SpecificSorter(Duration duration) {
            super(duration);
        }

        @Override
        void startSorting(T[] unsortedArray) {
            Arrays.sort(unsortedArray);
        }

        @Override
        public Sorter<T> withMaxDuration(Duration maxDuration) {
            return new SpecificSorter<>(maxDuration);
        }
    }
}