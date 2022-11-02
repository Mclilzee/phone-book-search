package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JumpSearcherTest {

    private static Contact[] searchableContacts;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContacts = new Contact[]{
                new Contact("Mark zergberg"),
                new Contact("John Doe"),
                new Contact("Gly marksman"),
                new Contact("Emad doblos"),
                new Contact("Dengos sheklos"),
                new Contact("Geralt rivea"),
                new Contact("2342351", "Dongos With Long Name")
        };

        toFind = new Contact[]{
                new Contact("Gly marksman"),
                new Contact("Emad doblos")
        };
    }

    JumpSearcher searcher = new JumpSearcher(searchableContacts, toFind, new BubbleSorter<>(Duration.ofDays(1)));

    @Test
    void getProperSearchMessage() {
        String result = searcher.search();
        assertTrue(result.matches(
                "Start searching \\(bubble sort \\+ jump search\\)\\.{3}\n" +
                        "Found 2 / 2 entries. Time taken: \\d+ min\\. \\d+ sec. \\d+ ms.\n" +
                        "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\.\n" +
                        "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."
        ));
    }

    @Test
    void getProperMessageWhenSortIsInterrupted() {
        JumpSearcher searcher = new JumpSearcher(searchableContacts, toFind, new BubbleSorter<>(Duration.ofSeconds(-1)));
        String result = searcher.search();
        assertTrue(result.matches("Start searching \\(bubble sort \\+ jump search\\)\\.{3}\n" +
                "Found 2 / 2 entries. Time taken: \\d+ min\\. \\d+ sec. \\d+ ms.\n" +
                "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\. - STOPPED, moved to linear search\n" +
                "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."));
    }
}