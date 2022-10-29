package project;

import org.junit.jupiter.api.Test;
import project.searchable.Record;

import static org.junit.jupiter.api.Assertions.*;

class RecordReaderTest {

    @Test
    void readFileToRecordArray() {
        Record[] expected = new Record[]{
                new Record("Mark zergberg"),
                new Record("John Doe"),
                new Record("Gly marksman"),
                new Record("Emad doblos"),
                new Record("Dengos sheklos"),
                new Record("Geralt rivea"),
                new Record("2342351", "Dongos With Long Name")
        };
        Record[] result = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        assertArrayEquals(expected, result);

        Record[] expected2 = new Record[]{
                new Record("Gly marksman"),
                new Record("Emad doblos")
        };
        Record[] result2 = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
        assertArrayEquals(expected2, result2);
    }

    @Test
    void returnEmptyArrayIfFileNotFound() {
        Record[] expected = new Record[0];
        Record[] result = RecordReader.readFileToRecordArray("imaginaryfile");
        assertArrayEquals(expected, result);
    }
}