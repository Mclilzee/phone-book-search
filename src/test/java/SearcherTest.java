import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearcherTest {

    private final File toFind = new File("./src/test/java/sampleToFind.txt");
    private final File searchableFile = new File("./src/test/java/sampleSearchableFile.txt");

    Searcher searcher = mock(Searcher.class, withSettings().useConstructor(toFind, searchableFile)
            .defaultAnswer(CALLS_REAL_METHODS));

    @Test
    void getStringArrayFromFile() {
        String[] expected = new String[]{"Gly marksman", "Emad doblos"};
        assertArrayEquals(expected, searcher.readFileIntoStringArray(toFind));
    }

    @Test
    void getEmptyArrayIfFileDoesNotExit() {
        assertArrayEquals(new String[0], searcher.readFileIntoStringArray(new File("imaginaryFile")));
    }

    @Test
    void getProperMessageWithCorrectFound() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getFormattedMessage(start, end, 250);
        String expected = "Found 250 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithCorrectTotal() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getFormattedMessage(start, end, 250);
        String expected = "Found 250 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithMinutes() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(300);

        String message = searcher.getFormattedMessage(start, end, 2);
        String expected = "Found 2 / 2 entries. Time taken: 4 min. 0 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithSeconds() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofSeconds(320);

        String message = searcher.getFormattedMessage(start, end, 1);
        String expected = "Found 1 / 2 entries. Time taken: 4 min. 20 sec. 0 ms.";
        assertEquals(expected, message);
    }

    @Test
    void getProperMessageWithMillis() {
        Duration start = Duration.ofSeconds(60);
        Duration end = Duration.ofMillis(320020);

        String message = searcher.getFormattedMessage(start, end, 400);
        String expected = "Found 400 / 2 entries. Time taken: 4 min. 20 sec. 20 ms.";
        assertEquals(expected, message);
    }

    @Test
    void searchFunctionReturnsProperMessage() {
        Searcher someSearcher = new Searcher(toFind, searchableFile) {
            @Override
            protected int foundSubArrayElements() {
                return 2;
            }

            @Override
            protected boolean isElementInSearchableFile(String element) {
                return false;
            }
        };
        assertTrue(someSearcher.search().matches("Found 2 / 2 entries\\. Time taken: \\d+ min. \\d+ sec\\. \\d+ ms\\."));
    }
}