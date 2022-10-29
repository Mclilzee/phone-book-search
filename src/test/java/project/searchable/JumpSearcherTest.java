package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import project.RecordReader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JumpSearcherTest {

    private static Record[] searchableRecords;
    private static Record[] toFind;

    @BeforeAll
    static void setup() {
        searchableRecords = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        toFind = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
    }

    JumpSearcher searcher = new JumpSearcher(searchableRecords, toFind, Duration.ofDays(1));

    @Test
    void sortingDuration() {
        assertEquals(Duration.ZERO, searcher.getSortingDuration());

        Duration start = Duration.ofSeconds(200);
        Duration end = Duration.ofSeconds(350);
        searcher.setSortingDuration(start, end);
        assertEquals(Duration.ofSeconds(150), searcher.getSortingDuration());
    }
    @Disabled
    @Test
    void getProperSearchMessage() {
        assertTrue(searcher.search().matches(
                "Start searching \\(bubble sort \\+ jump search\\)\\.{3}\\n" +
                        "Found 500 / 500 entries\\. Time taken: \\d+ min\\. \\d+ sec\\. \\d+ ms\\.\\n" +
                        "Sorting time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\." +
                        "Searching time: \\d+ min\\. \\d+ sec\\. \\d+ ms\\."
        ));
    }
}