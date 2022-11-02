package project.searchable;

import java.time.Duration;

public abstract class ContactSorter<T extends Comparable<T>> {
    private final Duration maxDuration;
    Duration currentDuration = Duration.ZERO;
    private boolean finishedSorting;

    public ContactSorter(Duration duration) {
        this.maxDuration = duration;
        this.finishedSorting = false;
    }

    public T[] getSorted(T[] unsortedArray) {
        Duration start = Duration.ofMillis(System.currentTimeMillis());

        startSorting(unsortedArray);
        stopWhenMaxDurationExceeded();
        finishedSorting = true;

        Duration end = Duration.ofMillis(System.currentTimeMillis());
        setCurrentDuration(start, end);
        return unsortedArray;
    }

    private void stopWhenMaxDurationExceeded() {
        if (maxDuration.isNegative()) {
            throw new RuntimeException();
        }

        Thread timeTest = new Thread(() -> {
            try {
                Thread.sleep(maxDuration.toMillis());
                if (!finishedSorting) {
                    setToMaxDuration();
                    throw new RuntimeException();
                }
            } catch (InterruptedException ignored) {
            }
        });

        timeTest.start();
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
