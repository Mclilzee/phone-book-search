import searchable.LinearSearcher;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File searchableFile = new File("./phonebook.txt");
        File toFind = new File("./find.txt");

        LinearSearcher linearSearcher = new LinearSearcher(toFind, searchableFile);
        System.out.println(linearSearcher.search());
    }
}
