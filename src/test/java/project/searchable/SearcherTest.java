package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearcherTest {

    private static Contact[] searchableContacts;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContacts = new Contact[]{
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

    Searcher searcher = mock(Searcher.class, withSettings().useConstructor(searchableContacts, toFind)
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
    void findElements() {
        Searcher specificSearcher = new Searcher(searchableContacts, toFind) {
            @Override
            public String search() {
                return null;
            }

            @Override
            boolean isElementInSearchableFile(Contact element) {
                return true;
            }
        };

        specificSearcher.findElements();
        assertEquals(2, specificSearcher.getFound());
    }

    @Test
    void getProperMessageWithCorrectFound() {
        Searcher specificSearcher = new Searcher(searchableContacts, toFind) {
            @Override
            public String search() {
                return null;
            }

            @Override
            boolean isElementInSearchableFile(Contact element) {
                return true;
            }
        };

        specificSearcher.findElements();
        specificSearcher.searchDuration = Duration.ofSeconds(240);
        String message = specificSearcher.getFoundMessage();
        String expected = "Found 2 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
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
        assertTrue(specificSearcher.isElementInSearchableFile(new Contact("123421", "John Doe")));
        assertTrue(specificSearcher.isElementInSearchableFile(new Contact("123421", "Dongos With Long Name")));
        assertFalse(specificSearcher.isElementInSearchableFile(new Contact("Marksmoon Walker")));
    }

    private static Stream<Arguments> provideSearcher() {
        return Stream.of(
                Arguments.of(new LinearSearcher(searchableContacts, toFind)),
                Arguments.of(new JumpSearcher(searchableContacts, toFind))
        );
    }
}