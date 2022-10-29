package project.searchable;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void bubbleSort() {
        ContactSorter.bubbleSort(contacts, Duration.ofMinutes(100));
        assertArrayEquals(contacts, expected);
    }

    @Test
    void bubbleSorterThrowErrorWhenExceedsTime() {
        assertThrows(RuntimeException.class, () -> ContactSorter.bubbleSort(contacts, Duration.ofSeconds(-1)));
    }
}