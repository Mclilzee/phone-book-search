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

        Sorter<Contact> bubbleSorter = new BubbleSorter<>(linearSearcher.getSearchDuration().multipliedBy(10));
        Sorter<Contact> quickSorter = new QuickSorter<>(linearSearcher.getSearchDuration().multipliedBy(10));

        System.out.println("Start searching (bubble sort + jump search)...");
        JumpSearcher<Contact> jumpSearcher = new JumpSearcher<>(SEARCHABLE_CONTACTS, TO_FIND, bubbleSorter);
        System.out.println(jumpSearcher.search());
        System.out.println();

        System.out.println("Start searching (quick sort + jump search)...");
        JumpSearcher<Contact> jumpSearchQuickSort = new JumpSearcher<>(SEARCHABLE_CONTACTS, TO_FIND, quickSorter);
        System.out.println(jumpSearchQuickSort.search());
        System.out.println();

        System.out.println("Start searching (quick sort + binary search)...");
        BinarySearcher<Contact> binarySearcher = new BinarySearcher<>(SEARCHABLE_CONTACTS, TO_FIND, quickSorter);
        System.out.println(binarySearcher.search());
    }
}
