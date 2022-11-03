package project.searchable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    Contact[] expected = new Contact[]{
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Catman bro"),
            new Contact("1234", "Gly Marksman"),
            new Contact("John Do"),
            new Contact("John Doe"),
            new Contact("Mark zergberg"),
    };

    Contact[] content;

    @BeforeEach
    void unsortedContent() {
        content = new Contact[]{
                new Contact("Mark zergberg"),
                new Contact("John Doe"),
                new Contact("1234", "Gly Marksman"),
                new Contact("Ahmad Asmar Zyad"),
                new Contact("Ahmad Asmar Zahra"),
                new Contact("Catman bro"),
                new Contact("John Do")
        };
    }

    Sorter<Contact> sorter = new SpecificSorter<>(Duration.ofSeconds(10));

    @Test
    void sorterThrowErrorWhenExceedsTime() {
        sorter.setMaxDuration(Duration.ofSeconds(-1));
        assertThrows(InterruptedException.class, () -> sorter.getSorted(content));
    }

    @Test
    void setMaxLimitCorrectly() {
        sorter.setMaxDuration(Duration.ofDays(1));
        assertEquals(Duration.ofDays(1), sorter.getMaxDuration());
    }

    @Test
    void getSorted() throws InterruptedException {
        assertArrayEquals(sorter.getSorted(content), expected);
    }

    @ParameterizedTest
    @MethodSource("provideSorter")
    void testSortingAlgorithm(Sorter<Contact> sorter) {
        sorter.startSorting(content);
        assertArrayEquals(expected, content);
    }

    private static Stream<Arguments> provideSorter() {
        return Stream.of(
                Arguments.of(new BubbleSorter<>(Duration.ofSeconds(10))),
                Arguments.of(new QuickSorter<>(Duration.ofSeconds(10)))
        );
    }

    private static class SpecificSorter<T extends Comparable<T>> extends Sorter<T> {

        public SpecificSorter(Duration duration) {
            super(duration);
        }

        @Override
        void startSorting(T[] unsortedArray) {
            Arrays.sort(unsortedArray);
        }
    }
}