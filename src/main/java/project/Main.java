package project;

import project.searchable.*;

public class Main {
    private static final Contact[] SEARCHABLE_CONTACTS = ContactReader.readFileToContactArray("./phonebook.txt");
    private static final Contact[] TO_FIND = ContactReader.readFileToContactArray("./find.txt");

    public static void main(String[] args) {
        LinearSearcher linearSearcher = new LinearSearcher(SEARCHABLE_CONTACTS, TO_FIND);
        System.out.println(linearSearcher.search());
        System.out.println();

        ContactSorter<Contact> bubbleSorter = new BubbleSorter<>(linearSearcher.getSearchDuration().multipliedBy(1));
        JumpSearcher jumpSearcher = new JumpSearcher(SEARCHABLE_CONTACTS, TO_FIND, bubbleSorter);
        System.out.println(jumpSearcher.search());
    }
}
