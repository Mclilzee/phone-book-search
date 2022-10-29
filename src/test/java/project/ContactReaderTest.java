package project;

import org.junit.jupiter.api.Test;
import project.searchable.Contact;

import static org.junit.jupiter.api.Assertions.*;

class ContactReaderTest {

    @Test
    void readFileToRecordArray() {
        Contact[] expected = new Contact[]{
                new Contact("Mark zergberg"),
                new Contact("John Doe"),
                new Contact("Gly marksman"),
                new Contact("Emad doblos"),
                new Contact("Dengos sheklos"),
                new Contact("Geralt rivea"),
                new Contact("2342351", "Dongos With Long Name")
        };
        Contact[] result = RecordReader.readFileToRecordArray("./src/test/java/project/sampleSearchableFile.txt");
        assertArrayEquals(expected, result);

        Contact[] expected2 = new Contact[]{
                new Contact("Gly marksman"),
                new Contact("Emad doblos")
        };
        Contact[] result2 = RecordReader.readFileToRecordArray("./src/test/java/project/sampleToFind.txt");
        assertArrayEquals(expected2, result2);
    }

    @Test
    void returnEmptyArrayIfFileNotFound() {
        Contact[] expected = new Contact[0];
        Contact[] result = RecordReader.readFileToRecordArray("imaginaryfile");
        assertArrayEquals(expected, result);
    }
}