package project.searchable;

public class BinarySearcher<T extends Comparable<T>> extends Searcher<T> {

    public BinarySearcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
        super(searchableContent, toFind, sorter);
    }

    public BinarySearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    boolean isElementInSearchableFile(T element) {
        int startIndex = 0;
        int endIndex = searchableContent.length - 1;
        int middleIndex = (startIndex + endIndex) / 2;

        T compareElement;

        while (startIndex <= endIndex && endIndex >= startIndex) {
            compareElement = searchableContent[middleIndex];

            if (element.compareTo(compareElement) > 0) {
                startIndex = middleIndex + 1;
                middleIndex = (startIndex + endIndex) / 2;
            } else if (element.compareTo(compareElement) < 0) {
                endIndex = middleIndex - 1;
                middleIndex = (startIndex + endIndex) / 2;
            }

            if (element.compareTo(compareElement) == 0) {
                return true;
            }
        }

        return false;
    }
}
