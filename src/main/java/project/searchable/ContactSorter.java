package project.searchable;

import java.time.Duration;

public abstract class ContactSorter<T extends Comparable<T>> {

    private final Duration maxDuration;
    Duration currentDuration = Duration.ZERO;

    public ContactSorter(Duration duration) {
        this.maxDuration = duration;
    }

    public abstract T[] getSorted(T[] unsortedRecords);

    public abstract ContactSorter<T> withMaxDuration(Duration maxDuration);

    Duration getMaxDuration() {
        return maxDuration;
    }

    Duration getCurrentDuration() {
        return this.currentDuration;
    }

    void setCurrentDuration(Duration start, Duration end) {
        this.currentDuration = end.minus(start);
    }
}
