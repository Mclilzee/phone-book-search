package project;

import project.searchable.ContactSorter;
import project.searchable.JumpSearcher;
import project.searchable.LinearSearcher;
import project.searchable.Contact;

public class Main {
    private static final Contact[] SEARCHABLE_CONTACTS = ContactReader.readFileToContactArray("./phonebook.txt");
    private static final Contact[] TO_FIND = ContactReader.readFileToContactArray("./find.txt");

    public static void main(String[] args) {
        LinearSearcher linearSearcher = new LinearSearcher(SEARCHABLE_CONTACTS, TO_FIND);
        System.out.println(linearSearcher.search());
        System.out.println();

        ContactSorter.setMaxDuration(linearSearcher.getSearchDuration().multipliedBy(10));
        JumpSearcher jumpSearcher = new JumpSearcher(SEARCHABLE_CONTACTS, TO_FIND);
        System.out.println(jumpSearcher.search());
    }
}
