package searchable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearcherTest {

    private static final File toFind = new File("./src/test/java/searchable/sampleToFind.txt");
    private static final File searchableFile = new File("./src/test/java/searchable/sampleSearchableFile.txt");

    Searcher searcher = mock(Searcher.class, withSettings().useConstructor(toFind, searchableFile)
            .defaultAnswer(CALLS_REAL_METHODS));

    @Test
    void getRecordArray() {
        Record[] expected = new Record[]{
                new Record("Gly marksman"),
                new Record("2341", "Emad doblos")
        };
        assertArrayEquals(expected, searcher.readFileToRecordArray(toFind));
    }

    @Test
    void getEmptyArrayIfFileDoesNotExit() {
        assertArrayEquals(new String[0], searcher.readFileToRecordArray(new File("imaginaryFile")));
    }

    @Test
    void getProperMessageWithCorrectFound() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getFoundMessage(start, end, 250);
        String expected = "Found 250 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithCorrectTotal() {
        Duration start = Duration.ofMillis(20145);
        Duration end = Duration.ofMillis(100395);

        String message = searcher.getDurationString(start, end);
        String expected = "1 min. 20 sec. 250 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithMinutes() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getDurationString(start, end);
        String expected = "4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithSeconds() {
        Duration start = Duration.ofSeconds(20);
        Duration end = Duration.ofSeconds(40);

        String message = searcher.getDurationString(start, end);
        String expected = "0 min. 20 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperDurationStringWithMillis() {
        Duration start = Duration.ofMillis(200);
        Duration end = Duration.ofMillis(400);

        String message = searcher.getDurationString(start, end);
        String expected = "0 min. 0 sec. 200 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getRecordFromStringTest() {
        Record expected = new Record("123", "John Doe");
        Record actual = searcher.getRecordFromString("John Doe");
        Record actual2 = searcher.getRecordFromString("123534 John Doe");
        assertEquals(expected, actual);
        assertEquals(expected, actual2);
    }

    @ParameterizedTest
    @MethodSource("provideSearcher")
    void checkIfElementIsInArray(Searcher specificSearcher) {
        assertTrue(specificSearcher.isElementInSearchableFile(new Record("123421", "John Doe")));
        assertTrue(specificSearcher.isElementInSearchableFile(new Record("123421", "Dongos With Long Name")));
        assertFalse(specificSearcher.isElementInSearchableFile(new Record("Marksmoon Walker")));
    }

    @ParameterizedTest
    @MethodSource("provideSearcher")
    void countElementsOfSubArray(Searcher specificSearcher) {
        assertEquals(2, specificSearcher.numberOfElementsFound());
    }

    private static Stream<Arguments> provideSearcher() {
        return Stream.of(
                Arguments.of(new LinearSearcher(toFind, searchableFile)),
                Arguments.of(new JumpSearcher(toFind, searchableFile))
        );
    }
}