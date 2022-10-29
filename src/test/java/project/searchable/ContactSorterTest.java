package project.searchable;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ContactSorterTest {

    Contact[] contacts = new Contact[]{
            new Contact("Mark zergberg"),
            new Contact("John Doe"),
            new Contact("1234", "Gly Marksman"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Catman bro"),
            new Contact("John Do")
    };

    Contact[] expected = new Contact[]{
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Catman bro"),
            new Contact("1234", "Gly Marksman"),
            new Contact("John Do"),
            new Contact("John Doe"),
            new Contact("Mark zergberg"),
    };

    @Test
    void bubbleSorterThrowErrorWhenExceedsTime() {
        ContactSorter.setMaxDuration(Duration.ofSeconds(-1));
        assertThrows(RuntimeException.class, () -> ContactSorter.bubbleSort(contacts));
    }

    @Test
    void setMaxLimitCorrectly() {
        ContactSorter.setMaxDuration(Duration.ofDays(1));
        assertEquals(Duration.ofDays(1), ContactSorter.getMaxDuration());
    }

    @Test
    void bubbleSort() {
        ContactSorter.bubbleSort(contacts);
        assertArrayEquals(contacts, expected);
    }


}