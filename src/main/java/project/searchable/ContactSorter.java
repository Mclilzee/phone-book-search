package project.searchable;

import java.time.Duration;

public abstract class ContactSorter<T extends Comparable<T>> {

    private final Duration maxDuration;

    public ContactSorter(Duration duration) {
        this.maxDuration = duration;
    }

    public abstract T[] getSorted(T[] unsortedRecords);

    public abstract ContactSorter<T> withMaxDuration(Duration maxDuration);

    public Duration getMaxDuration() {
        return maxDuration;
    }
}
