package project.searchable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class QuickSorter<T extends Comparable<T>> extends Sorter<T> {

    public QuickSorter(Duration duration) {
        super(duration);
    }

    @Override
    void startSorting(T[] content) {
        List<T> contentList = List.of(content);
        quickSortList(contentList).toArray(content);
    }

    private List<T> quickSortList(List<T> contentList) {
        if (contentList.size() <= 1) {
            return contentList;
        }

        return getCombinedResult(contentList);
    }

    private List<T> getCombinedResult(List<T> contentList) {
        T pivot = contentList.get(contentList.size() / 2);
        List<T> smaller = new ArrayList<>();
        List<T> larger = new ArrayList<>();

        fillLists(contentList, pivot, smaller, larger);

        List<T> combine = new ArrayList<>(quickSortList(smaller));
        combine.add(pivot);
        combine.addAll(quickSortList(larger));
        return combine;
    }

    private void fillLists(List<T> contentList, T pivot, List<T> smaller, List<T> larger) {
        for (T t : contentList) {
            if (Thread.interrupted()) {
                return;
            }

            if (t.compareTo(pivot) < 0) {
                smaller.add(t);
            } else if (t.compareTo(pivot) > 0)
                larger.add(t);
        }
    }
}
