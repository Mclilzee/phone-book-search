package project;

import project.searchable.JumpSearcher;
import project.searchable.LinearSearcher;
import project.searchable.Contact;

public class Main {
    private static final Contact[] SEARCHABLE_CONTACTS = RecordReader.readFileToRecordArray("./phonebook.txt");
    private static final Contact[] TO_FIND = RecordReader.readFileToRecordArray("./find.txt");

    public static void main(String[] args) {
        LinearSearcher linearSearcher = new LinearSearcher(SEARCHABLE_CONTACTS, TO_FIND);
        System.out.println(linearSearcher.search());
        System.out.println();

        JumpSearcher jumpSearcher = new JumpSearcher(SEARCHABLE_CONTACTS, TO_FIND, linearSearcher.getSearchDuration());
        System.out.println(jumpSearcher.search());
    }
}
