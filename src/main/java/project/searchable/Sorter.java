package project.searchable;

import java.time.Duration;
import java.util.Arrays;

public abstract class Sorter<T extends Comparable<T>> {
    private final Duration maxDuration;
    Duration currentDuration = Duration.ZERO;

    public Sorter(Duration duration) {
        this.maxDuration = duration;
    }

    abstract void startSorting(T[] unsortedArray);

    public abstract Sorter<T> withMaxDuration(Duration maxDuration);

    public T[] getSorted(T[] unsortedArray) throws InterruptedException {
        T[] copyArray = Arrays.copyOf(unsortedArray, unsortedArray.length);

        Duration start = Duration.ofMillis(System.currentTimeMillis());
        Thread sortingThread = new Thread(() -> startSorting(copyArray));
        sortingThread.start();

        while (sortingThread.isAlive()) {
            if (Duration.ofMillis(System.currentTimeMillis()).minus(start).compareTo(maxDuration) >= 0) {
                sortingThread.interrupt();
                setCurrentDurationToMax();
                throw new InterruptedException();
            }
        }

        setCurrentDuration(start, Duration.ofMillis(System.currentTimeMillis()));
        return copyArray;
    }

    Duration getMaxDuration() {
        return maxDuration;
    }

    Duration getCurrentDuration() {
        return this.currentDuration;
    }

    void setCurrentDuration(Duration start, Duration end) {
        this.currentDuration = end.minus(start);
    }

    void setCurrentDurationToMax() {
        this.currentDuration = maxDuration.isNegative() ? Duration.ZERO : maxDuration;
    }
}
