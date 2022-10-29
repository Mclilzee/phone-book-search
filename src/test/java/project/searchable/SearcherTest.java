package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import project.RecordReader;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearcherTest {

    private static Record[] searchableRecords;
    private static Record[] toFind;

    @BeforeAll
    static void setup() {
        searchableRecords = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        toFind = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
    }

    Searcher searcher = mock(Searcher.class, withSettings().useConstructor(searchableRecords, toFind)
            .defaultAnswer(CALLS_REAL_METHODS));

    @Test
    void searchDuration() {
        assertEquals(Duration.ZERO, searcher.getSearchDuration());

        Duration start = Duration.ofSeconds(200);
        Duration end = Duration.ofSeconds(500);
        searcher.setSearchDuration(start, end);

        assertEquals(Duration.ofSeconds(300), searcher.getSearchDuration());
    }

    @Test
    void numberOfElementsFound() {
        Searcher specificSearcher = new Searcher(searchableRecords, toFind) {
            @Override
            String search() {
                return null;
            }

            @Override
            boolean isElementInSearchableFile(Record element) {
                return true;
            }
        };
        assertEquals(2, specificSearcher.numberOfElementsFound());
    }

    @Test
    void getProperMessageWithCorrectFound() {
        searcher.searchDuration = Duration.ofSeconds(240);
        String message = searcher.getFoundMessage(250);
        String expected = "Found 250 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
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
    void checkIfElementIsInArray(Searcher specificSearcher) {
        assertTrue(specificSearcher.isElementInSearchableFile(new Record("123421", "John Doe")));
        assertTrue(specificSearcher.isElementInSearchableFile(new Record("123421", "Dongos With Long Name")));
        assertFalse(specificSearcher.isElementInSearchableFile(new Record("Marksmoon Walker")));
    }

    private static Stream<Arguments> provideSearcher() {
        return Stream.of(
                Arguments.of(new LinearSearcher(searchableRecords, toFind)),
                Arguments.of(new JumpSearcher(searchableRecords, toFind))
        );
    }
}