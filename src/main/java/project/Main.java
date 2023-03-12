package project;

import project.searchable.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
          System.out.println("Need to provide path to searchable contact, and contact to search");
          System.exit(-1);
        }

        Contact[] searchableContacts = ContactReader.readFileToContactArray("./phonebook.txt");
        Contact[] toFind = ContactReader.readFileToContactArray("./find.txt");
        processSearching(searchableContacts, toFind);
    }

    public static void processSearching(Contact[] searchableContacts, Contact[] toFind) {
        System.out.println("Start searching (linear search)...");
        LinearSearcher<Contact> linearSearcher = new LinearSearcher<>(searchableContacts, toFind);
        System.out.println(linearSearcher.search());
        System.out.println();

        Sorter<Contact> bubbleSorter = new BubbleSorter<>(linearSearcher.getSearchDuration().multipliedBy(1));
        Sorter<Contact> quickSorter = new QuickSorter<>(linearSearcher.getSearchDuration().multipliedBy(10));

        System.out.println("Start searching (bubble sort + jump search)...");
        JumpSearcher<Contact> jumpSearcher = new JumpSearcher<>(searchableContacts, toFind, bubbleSorter);
        System.out.println(jumpSearcher.search());
        System.out.println();

        System.out.println("Start searching (quick sort + jump search)...");
        JumpSearcher<Contact> jumpSearchQuickSort = new JumpSearcher<>(searchableContacts, toFind, quickSorter);
        System.out.println(jumpSearchQuickSort.search());
        System.out.println();

        System.out.println("Start searching (quick sort + binary search)...");
        BinarySearcher<Contact> binarySearcher = new BinarySearcher<>(searchableContacts, toFind, quickSorter);
        System.out.println(binarySearcher.search());
        System.out.println();

        System.out.println("Start searching (hash table search)...");
        ContactHashSearch hashSearch = new ContactHashSearch(searchableContacts, toFind);
        System.out.println(hashSearch.search());

    }
}
