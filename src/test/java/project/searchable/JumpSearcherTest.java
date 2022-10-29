package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import project.ContactReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JumpSearcherTest {

    private static Contact[] searchableContacts;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContacts = ContactReader.readFileToContactArray("./src/test/java/project/sampleSearchableFile.txt");
        toFind = ContactReader.readFileToContactArray("./src/test/java/project/sampleToFind.txt");
    }

    JumpSearcher searcher = new JumpSearcher(searchableContacts, toFind, Duration.ofDays(1));

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
        JumpSearcher searcher = new JumpSearcher(searchableContacts, toFind, Duration.ofSeconds(-1));
        String result = searcher.search();
        assertTrue(result.matches("Start searching \\(bubble sort \\+ jump search\\)\\.{3}\n" +
                "Found 2 / 2 entries. Time taken: \\d+ min\\. \\d+ sec. \\d+ ms.\n" +
                "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\. - STOPPED, moved to linear search\n" +
                "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."));
    }
}