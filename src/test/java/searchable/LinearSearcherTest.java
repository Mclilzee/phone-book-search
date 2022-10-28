package searchable;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LinearSearcherTest {

    private static final File toFind = new File("./src/test/java/searchable/sampleToFind.txt");
    private static final File searchableFile = new File("./src/test/java/searchable/sampleSearchableFile.txt");

    private final LinearSearcher searcher = new LinearSearcher(toFind, searchableFile);

    @Test
    void checkIfElementIsInArray() {
        assertTrue(searcher.isElementInSearchableFile("John Doe"));
        assertFalse(searcher.isElementInSearchableFile("Something man"));
    }

    @Test
    void countElementsOfSubArray() {
        assertEquals(2, searcher.foundSubArrayElements());
    }
}