package project.searchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecordTest {

    private Record record = new Record("1234", "John Doe");

    @Test
    void getNumber() {
        assertEquals("1234", record.getNumber());
    }

    @Test
    void getName() {
        assertEquals("John Doe", record.getName());
    }

    @Test
    void recordCreatedWithOneParameters() {
        record = new Record("John Doe");
        assertEquals("John Doe", record.getName());
        assertEquals("", record.getNumber());
    }

    @Test
    void recordsAreEqualIfTheyHaveSameFullName() {
        Record record2 = new Record("John Doe");
        assertEquals(record, record2);
    }

    @Test
    void compareTo() {
        Record other = new Record("23523", "Zerg man");
        assertTrue(record.compareTo(other) < 0);
        assertTrue(other.compareTo(record) > 0);

        Record equalRecord = new Record("John Doe");
        assertEquals(0, equalRecord.compareTo(record));
    }
}