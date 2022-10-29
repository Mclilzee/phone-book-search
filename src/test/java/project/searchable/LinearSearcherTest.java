package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import project.RecordReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LinearSearcherTest {

    private static Contact[] searchableContacts;
    private static Contact[] toFind;

    @BeforeAll
    static void setup() {
        searchableContacts = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        toFind = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
    }

    private final LinearSearcher searcher = new LinearSearcher(searchableContacts, toFind);

    @Test
    void searchFunctionReturnCorrectString() {
        assertTrue(searcher.search().matches("Start searching \\(linear search\\)\\.{3}\\n" +
                "Found 2 / 2 entries\\. Time taken: \\d+ min. \\d+ sec\\. \\d+ ms\\."));
    }
}