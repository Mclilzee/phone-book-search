package project.searchable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import project.RecordReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JumpSearcherTest {

    private static Record[] searchableRecords;
    private static Record[] toFind;

    @BeforeAll
    static void setup() {
        searchableRecords = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        toFind = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
        System.out.println("called once");
    }

    JumpSearcher searcher = new JumpSearcher(searchableRecords, toFind);

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