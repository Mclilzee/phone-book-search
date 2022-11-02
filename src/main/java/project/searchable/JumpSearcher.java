package project.searchable;

public class JumpSearcher extends Searcher {

    private boolean sortInterrupted = false;

    public JumpSearcher(Contact[] searchableContacts, Contact[] toFind, Sorter<Contact> sorter) {
        super(searchableContacts, toFind, sorter);
    }

    public JumpSearcher(Contact[] searchableContacts, Contact[] toFind) {
        super(searchableContacts, toFind);
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
            message = searchingMessage(searcher);
        }

        return message;
    }

    String searchingMessage(Searcher searcher) {
        StringBuilder builder = new StringBuilder("Start searching (bubble sort + jump search)...\n");
        builder.append(searcher.getFoundMessage(sorter.currentDuration)).append("\n");
        builder.append("Sorting time: ").append(getDurationString(sorter.getCurrentDuration()));
        if (searcher instanceof LinearSearcher) {
            builder.append(" - STOPPED, moved to linear search");
        }
        builder.append("\nSearching time: ").append(getDurationString(searcher.searchDuration));

        return builder.toString();
    }

    private void bubbleSortData() {
        try {
            this.searchableContacts = sorter.getSorted(this.searchableContacts);
        } catch (InterruptedException e) {
            sortInterrupted = true;
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
