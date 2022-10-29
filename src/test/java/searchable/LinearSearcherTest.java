package searchable;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LinearSearcherTest {

    private static final File toFind = new File("./src/test/java/searchable/sampleToFind.txt");
    private static final File searchableFile = new File("./src/test/java/searchable/sampleSearchableFile.txt");

    private final LinearSearcher searcher = new LinearSearcher(toFind, searchableFile);

    @Test
    void searchFunctionReturnCorrectString() {
        assertTrue(searcher.search().matches("Start searching \\(linear search\\)\\.{3}\\n" +
                "Found 2 / 2 entries\\. Time taken: \\d+ min. \\d+ sec\\. \\d+ ms\\."));
    }
}