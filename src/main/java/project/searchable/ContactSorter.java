package project.searchable;

import java.time.Duration;

public abstract class ContactSorter<T extends Comparable<T>> {
    private final Duration maxDuration;
    Duration currentDuration = Duration.ZERO;

    public ContactSorter(Duration duration) {
        this.maxDuration = duration;
    }

    public T[] getSorted(T[] unsortedArray) {
        Duration start = Duration.ofMillis(System.currentTimeMillis());

        Thread timeLimitThread = getTimeLimitThread();
        unsortedArray = startSorting(unsortedArray);

        // stop thread from throwing error if sorting finished before time limit
        timeLimitThread.interrupt();

        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setCurrentDuration(start, end);
        return unsortedArray;
    }

    private Thread getTimeLimitThread() {
        if (maxDuration.compareTo(Duration.ZERO) < 0) {
            setToMaxDuration();
            throw new RuntimeException();
        }

        return new Thread(() -> {
            try {
                Thread.sleep(maxDuration.toMillis());
            } catch (InterruptedException e) {
                setToMaxDuration();
                throw new RuntimeException();
            }
        });
    }

    abstract T[] startSorting(T[] unsortedArray);

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

    void setToMaxDuration() {
        this.currentDuration = maxDuration;
    }
}
