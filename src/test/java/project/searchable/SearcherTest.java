package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {

    private static Contact[] searchableContent;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContent = new Contact[]{
                new Contact("Dengos sheklos"),
                new Contact("2342351", "Dongos With Long Name"),
                new Contact("Emad doblos"),
                new Contact("Geralt rivea"),
                new Contact("Gly marksman"),
                new Contact("John Doe"),
                new Contact("Mark zergberg")
        };

        toFind = new Contact[]{
                new Contact("Gly marksman"),
                new Contact("Emad doblos")
        };
    }

    TestSearcher<Contact> searcher = new TestSearcher<>(searchableContent, toFind);

    @Test
    void searchDuration() {
        assertEquals(Duration.ZERO, searcher.getSearchDuration());

        Duration start = Duration.ofSeconds(200);
        Duration end = Duration.ofSeconds(500);
        searcher.setSearchDuration(start, end);

        assertEquals(Duration.ofSeconds(300), searcher.getSearchDuration());
    }

    @Test
    void findElements() {
        searcher.findElements();
        assertEquals(2, searcher.getFound());
    }

    @Test
    void getProperMessageWithCorrectFound() {
        searcher.findElements();
        searcher.searchDuration = Duration.ofSeconds(240);
        String message = searcher.getFoundMessage(Duration.ZERO);
        String expected = "Found 2 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithCorrectSortingTime() {
        searcher.findElements();
        searcher.searchDuration = Duration.ofSeconds(240);
        String message = searcher.getFoundMessage(Duration.ofSeconds(1));
        String expected = "Found 2 / 2 entries. Time taken: 4 min. 1 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithCorrectTotal() {
        Duration start = Duration.ofMillis(20145);
        Duration end = Duration.ofMillis(100395);

        String message = searcher.getDurationString(end.minus(start));
        String expected = "1 min. 20 sec. 250 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithMinutes() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getDurationString(end.minus(start));
        String expected = "4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithSeconds() {
        Duration start = Duration.ofSeconds(20);
        Duration end = Duration.ofSeconds(40);

        String message = searcher.getDurationString(end.minus(start));
        String expected = "0 min. 20 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperDurationStringWithMillis() {
        Duration start = Duration.ofMillis(200);
        Duration end = Duration.ofMillis(400);

        String message = searcher.getDurationString(end.minus(start));
        String expected = "0 min. 0 sec. 200 ms.";
        assertEquals(expected, message);
    }

    @ParameterizedTest
    @MethodSource("provideSearcher")
    void checkIfElementIsInArray(Searcher<Contact> searcher) {
        assertTrue(searcher.isElementInSearchableFile(new Contact("123421", "John Doe")));
        assertTrue(searcher.isElementInSearchableFile(new Contact("123421", "Dongos With Long Name")));
        assertFalse(searcher.isElementInSearchableFile(new Contact("Marksmoon Walker")));
    }

    @ParameterizedTest
    @MethodSource("provideEmptySearcher")
    void checkIfElementIsInEmptyArray(Searcher<Contact> searcher) {

        assertFalse(searcher.isElementInSearchableFile(new Contact("123421", "John Doe")));
        assertFalse(searcher.isElementInSearchableFile(new Contact("123421", "Dongos With Long Name")));
        assertFalse(searcher.isElementInSearchableFile(new Contact("Marksmoon Walker")));
    }

    private static Stream<Arguments> provideSearcher() {
        return Stream.of(
                Arguments.of(new LinearSearcher<>(searchableContent, toFind)),
                Arguments.of(new JumpSearcher<>(searchableContent, toFind)),
                Arguments.of(new BinarySearcher<>(searchableContent, toFind))
        );
    }

    private static Stream<Arguments> provideEmptySearcher() {
        return Stream.of(
                Arguments.of(new LinearSearcher<>(new Contact[0], toFind)),
                Arguments.of(new JumpSearcher<>(new Contact[0], toFind)),
                Arguments.of(new BinarySearcher<>(new Contact[0], toFind))
        );
    }

    @Test
    void getProperSearchMessage() {
        String result = searcher.search();
        assertTrue(result.matches("Found 2 / 2 entries. Time taken: \\d+ min\\. \\d+ sec. \\d+ ms.\n" +
                "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\.\n" +
                "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."
        ));
    }

    @Test
    void getProperMessageWhenSortIsInterrupted() {
        searcher = new TestSearcher<>(searchableContent, toFind, new BubbleSorter<>(Duration.ofSeconds(-1)));
        String result = searcher.search();
        assertTrue(result.matches("Found 2 / 2 entries. Time taken: \\d+ min\\. \\d+ sec. \\d+ ms.\n" +
                "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\. - STOPPED, moved to linear search\n" +
                "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."));
    }

    private static class TestSearcher<T extends Comparable<T>> extends Searcher<T> {

        TestSearcher(T[] searchableContent, T[] toFind) {
            super(searchableContent, toFind);
        }

        TestSearcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
            super(searchableContent, toFind, sorter);
        }

        @Override
        boolean isElementInSearchableFile(T element) {
            return true;
        }
    }
}