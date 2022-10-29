package project.searchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RecordSorterTest {

    Record[] records = new Record[]{
            new Record("Mark zergberg"),
            new Record("John Doe"),
            new Record("1234", "Gly Marksman"),
            new Record("Ahmad Asmar Zyad"),
            new Record("Ahmad Asmar Zahra"),
            new Record("Catman bro"),
            new Record("John Do")
    };

    Record[] expected = new Record[]{
            new Record("Ahmad Asmar Zahra"),
            new Record("Ahmad Asmar Zyad"),
            new Record("Catman bro"),
            new Record("1234", "Gly Marksman"),
            new Record("John Do"),
            new Record("John Doe"),
            new Record("Mark zergberg"),
    };

    @Test
    void bubbleSort() {
        RecordSorter.bubbleSort(records);
        assertArrayEquals(records, expected);
    }
}