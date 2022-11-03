package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactHashSearchTest {

    private static Contact[] searchableContent;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContent = new Contact[]{
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

    ContactHashSearch searcher = new ContactHashSearch(searchableContent, toFind);

    @Test
    void getCorrectSearchMessage() {
        String result = searcher.search();
        assertTrue(result.matches("Found 2 / 2 entries. Time taken: \\\\d+ min\\\\. \\\\d+ sec. \\\\d+ ms.\n" +
                "Creating time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\.\n" +
                "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ m\\."));
    }
}