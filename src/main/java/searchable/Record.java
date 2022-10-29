package searchable;

public class Record {
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public Record(String phoneNumber, String firstName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Record(String firstName, String lastName) {
        this("", firstName, lastName);
    }

    public String getNumber() {
        return this.phoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
