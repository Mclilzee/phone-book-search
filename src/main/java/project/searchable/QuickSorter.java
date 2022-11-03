package project.searchable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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

        int pivotIndex = contentList.size() / 2;
        List<T> smaller = new ArrayList<>();
        List<T> larger = new ArrayList<>();
        T pivot = contentList.get(pivotIndex);

        for (int i = 0; i < contentList.size(); i++) {
            if (i == pivotIndex) {
                continue;
            }

            if (contentList.get(i).compareTo(pivot) < 0) {
                smaller.add(contentList.get(i));
            } else {
                larger.add(contentList.get(i));
            }
        }

        List<T> combine = new ArrayList<>(quickSortList(smaller));
        combine.add(pivot);
        combine.addAll(quickSortList(larger));

        return combine;
    }
}
