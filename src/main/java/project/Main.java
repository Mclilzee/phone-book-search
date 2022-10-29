package project;

import project.searchable.LinearSearcher;
import project.searchable.Record;

public class Main {
    private static final Record[] searchableRecords = RecordReader.readFileToRecordArray("./phonebook.txt");
    private static final Record[] toFind = RecordReader.readFileToRecordArray("./find.txt");

    public static void main(String[] args) {
        LinearSearcher linearSearcher = new LinearSearcher(searchableRecords, toFind);
        System.out.println(linearSearcher.search());
    }
}
