package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LinearSearcherTest {

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

    private final LinearSearcher searcher = new LinearSearcher(searchableContacts, toFind);

    @Test
    void searchFunctionReturnCorrectString() {
        assertTrue(searcher.search().matches("Start searching \\(linear search\\)\\.{3}\\n" +
                "Found 2 / 2 entries\\. Time taken: \\d+ min. \\d+ sec\\. \\d+ ms\\."));
    }
}