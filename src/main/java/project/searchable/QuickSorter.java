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
        int pivotIndex = contentList.size() / 2;
        List<T> smaller = new ArrayList<>();
        List<T> larger = new ArrayList<>();

        fillLists(contentList, pivotIndex, smaller, larger);

        List<T> combine = new ArrayList<>(quickSortList(smaller));
        combine.add(contentList.get(pivotIndex));
        combine.addAll(quickSortList(larger));
        return combine;
    }

    private void fillLists(List<T> contentList, int pivotIndex, List<T> smaller, List<T> larger) {
        T pivot = contentList.get(pivotIndex);
        for (int i = 0; i < contentList.size(); i++) {
            if (Thread.interrupted()) {
                return;
            }

            T element = contentList.get(i);

            if (pivotIndex == i) {
                continue;
            }

            if (element.compareTo(pivot) < 0) {
                smaller.add(element);
            } else {
                larger.add(element);
            }
        }
    }
}
