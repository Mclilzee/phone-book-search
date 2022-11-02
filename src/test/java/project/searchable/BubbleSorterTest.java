package project.searchable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

class BubbleSorterTest {

    BubbleSorter<Contact> sorter = new BubbleSorter<>(Duration.ofSeconds(10));
    Contact[] contacts;

    Contact[] expected = new Contact[]{
            new Contact("Ahmad Asmar Zahra"),
            new Contact("Ahmad Asmar Zyad"),
            new Contact("Catman bro"),
            new Contact("1234", "Gly Marksman"),
            new Contact("John Do"),
            new Contact("John Doe"),
            new Contact("Mark zergberg"),
    };

    @BeforeEach
    void unsortedContact() {
        contacts = new Contact[]{
                new Contact("Mark zergberg"),
                new Contact("John Doe"),
                new Contact("1234", "Gly Marksman"),
                new Contact("Ahmad Asmar Zyad"),
                new Contact("Ahmad Asmar Zahra"),
                new Contact("Catman bro"),
                new Contact("John Do")
        };
    }

    @Test
    void startSorting() {
        sorter.startSorting(contacts);
        assertArrayEquals(expected, contacts);
    }

    @Test
    void withMaxDuration() {
        ContactSorter<Contact> newSorter = sorter.withMaxDuration(Duration.ofDays(1));
        assertEquals(Duration.ofDays(1), newSorter.getMaxDuration());
    }
}