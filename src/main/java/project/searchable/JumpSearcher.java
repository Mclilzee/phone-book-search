package project.searchable;

public class JumpSearcher<T extends Comparable<T>> extends Searcher<T> {

    public JumpSearcher(T[] searchableContent, T[] toFind, Sorter<T> sorter) {
        super(searchableContent, toFind, sorter);
    }

    public JumpSearcher(T[] searchableContent, T[] toFind) {
        super(searchableContent, toFind);
    }

    @Override
    public String search() {
        String message;
        try {
            searchableContent = sorter.getSorted(searchableContent);
            this.findElements();
            message = searchingMessage(this);
        } catch (InterruptedException e) {
            LinearSearcher<T> searcher = new LinearSearcher<>(searchableContent, toFind);
            searcher.findElements();
            message = searchingMessage(searcher);
        }

        return message;
    }

    String searchingMessage(Searcher<T> searcher) {
        StringBuilder builder = new StringBuilder();
        builder.append(searcher.getFoundMessage(sorter.currentDuration)).append("\n");
        builder.append("Sorting time: ").append(getDurationString(sorter.getCurrentDuration()));
        if (searcher instanceof LinearSearcher) {
            builder.append(" - STOPPED, moved to linear search");
        }
        builder.append("\nSearching time: ").append(getDurationString(searcher.searchDuration));

        return builder.toString();
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
