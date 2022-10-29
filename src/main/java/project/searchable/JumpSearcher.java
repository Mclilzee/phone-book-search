package project.searchable;

import java.time.Duration;

public class JumpSearcher extends Searcher {

    private boolean sortInterrupted = false;
    private final Duration sortingLimit;

    public JumpSearcher(Contact[] searchableContacts, Contact[] toFind, Duration maxSortLimit) {
        super(searchableContacts, toFind);
        this.sortingLimit = maxSortLimit;
    }

    @Override
    public String search() {
        bubbleSortData();
        String message;
        if (!sortInterrupted) {
            this.findElements();
            message = searchingMessage(this);
        } else {
            LinearSearcher searcher = new LinearSearcher(searchableContacts, toFind);
            searcher.findElements();
            searcher.setSortingDuration(this.sortingDuration);
            message = searchingMessage(searcher);
        }

        return message;
    }

    String searchingMessage(Searcher searcher) {
        StringBuilder builder = new StringBuilder("Start searching (bubble sort + jump search)...\n");
        builder.append(searcher.getFoundMessage()).append("\n");
        builder.append("Sorting time: ").append(getDurationString(sortingDuration));
        if (searcher instanceof LinearSearcher) {
            builder.append(" - STOPPED, moved to linear search");
        }
        builder.append("\nSearching time: ").append(getDurationString(searcher.searchDuration));

        return builder.toString();
    }

    private void bubbleSortData() {
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        try {
            ContactSorter.bubbleSort(this.searchableContacts, this.sortingLimit.multipliedBy(10));
        } catch (RuntimeException e) {
            sortInterrupted = true;
        } finally {
            Duration end = Duration.ofMillis(System.currentTimeMillis());
            setSortingDuration(start, end);
        }
    }

    @Override
    boolean isElementInSearchableFile(Contact element) {
        int current = 0;
        int previous = 0;
        int skip = (int) Math.sqrt(this.searchableContacts.length);
        int last = this.searchableContacts.length - 1;

        while (element.compareTo(this.searchableContacts[current]) > 0) {
            if (current == last) {
                return false;
            }

            previous = current;
            current = Math.min(last, current + skip);
        }

        while (element.compareTo(this.searchableContacts[current]) < 0) {
            current--;

            if (current == previous) {
                return false;
            }
        }

        return element.equals(this.searchableContacts[current]);
    }
}
