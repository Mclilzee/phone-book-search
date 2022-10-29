package project.searchable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactTest {

    private Contact contact = new Contact("1234", "John Doe");

    @Test
    void getNumber() {
        assertEquals("1234", contact.getNumber());
    }

    @Test
    void getName() {
        assertEquals("John Doe", contact.getName());
    }

    @Test
    void contactCreatedWithOneParameters() {
        contact = new Contact("John Doe");
        assertEquals("John Doe", contact.getName());
        assertEquals("", contact.getNumber());
    }

    @Test
    void contactsAreEqualIfTheyHaveSameFullName() {
        Contact contact2 = new Contact("John Doe");
        assertEquals(contact, contact2);
    }

    @Test
    void compareTo() {
        Contact other = new Contact("23523", "Zerg man");
        assertTrue(contact.compareTo(other) < 0);
        assertTrue(other.compareTo(contact) > 0);

        Contact equalContact = new Contact("John Doe");
        assertEquals(0, equalContact.compareTo(contact));
    }
}