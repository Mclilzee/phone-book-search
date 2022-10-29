package searchable;

import java.util.Objects;

public class Record {
    private final String phoneNumber;
    private final String name;

    public Record(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Record(String name) {
        this("", name);
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return name.equalsIgnoreCase(record.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
