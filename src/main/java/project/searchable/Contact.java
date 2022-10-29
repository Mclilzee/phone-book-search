package project.searchable;

import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private final String phoneNumber;
    private final String name;

    public Contact(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Contact(String name) {
        this("", name);
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equalsIgnoreCase(contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
