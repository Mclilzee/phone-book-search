package searchable;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JumpSearcherTest {

    JumpSearcher searcher = new JumpSearcher(
            new File("./src/test/java/searchable/sampleToFind.txt"),
            new File("./src/test/java/searchable/sampleSearchableFile.txt")
            );

    @Test
    void search() {
    }
}