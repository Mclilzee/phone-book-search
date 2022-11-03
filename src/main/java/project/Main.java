package project;

import project.searchable.*;

public class Main {
    private static final Contact[] SEARCHABLE_CONTACTS = ContactReader.readFileToContactArray("./phonebook.txt");
    private static final Contact[] TO_FIND = ContactReader.readFileToContactArray("./find.txt");

    public static void main(String[] args) {
        System.out.println("Start searching (linear search)...");
        LinearSearcher<Contact> linearSearcher = new LinearSearcher<>(SEARCHABLE_CONTACTS, TO_FIND);
        System.out.println(linearSearcher.search());
        System.out.println();

        System.out.println("Start searching (bubble sort + jump search)...");
        Sorter<Contact> bubbleSorter = new BubbleSorter<>(linearSearcher.getSearchDuration().multipliedBy(1));
        JumpSearcher<Contact> jumpSearcher = new JumpSearcher<>(SEARCHABLE_CONTACTS, TO_FIND, bubbleSorter);
        System.out.println(jumpSearcher.search());
    }
}
