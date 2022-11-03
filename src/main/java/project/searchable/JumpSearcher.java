package project.searchable;

public class JumpSearcher<T extends Comparable<T>> extends Searcher<T> {

    public JumpSearcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
        super(searchableContent, toFind, sorter);
    }

    public JumpSearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    boolean isElementInSearchableFile(T element) {
        int current = 0;
        int previous = 0;
        int skip = (int) Math.sqrt(this.searchableContent.length);
        int last = this.searchableContent.length - 1;

        while (element.compareTo(this.searchableContent[current]) > 0) {
            if (current == last) {
                return false;
            }

            previous = current;
            current = Math.min(last, current + skip);
        }

        while (element.compareTo(this.searchableContent[current]) < 0) {
            current--;

            if (current == previous) {
                return false;
            }
        }

        return element.equals(this.searchableContent[current]);
    }
}
