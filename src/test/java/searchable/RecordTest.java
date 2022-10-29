package searchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    private Record record = new Record("1234", "John", "Doe");

    @Test
    void getNumber() {
        assertEquals("1234", record.getNumber());
    }

    @Test
    void getFirstName() {
        assertEquals("John", record.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Doe", record.getLastName());
    }

    @Test
    void getFullName() {
        assertEquals("John Doe", record.getFullName());
    }

}