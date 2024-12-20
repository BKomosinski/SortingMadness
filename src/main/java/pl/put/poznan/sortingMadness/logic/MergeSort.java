package pl.put.poznan.sortingMadness.logic;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort implements SortingInterface {
    private String name;

    public MergeSort() {
        this.name = "MergeSort";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public <T> SortResult<T> sort(ArrayList<T> data, Comparator<? super T> comparator, boolean descOrder) {
        long startTime = System.nanoTime();


        if (data.size() > 1) {
            int mid = data.size() / 2;
            ArrayList<T> left = new ArrayList<>(data.subList(0, mid));
            ArrayList<T> right = new ArrayList<>(data.subList(mid, data.size()));

            sort(left, comparator, descOrder);
            sort(right, comparator, descOrder);

            merge(data, left, right, comparator, descOrder);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        return new SortResult<>(data, duration);
    }

    private <T> void merge(ArrayList<T> data, ArrayList<T> left, ArrayList<T> right, Comparator<? super T> comparator, boolean descOrder) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            int comparison = comparator.compare(left.get(i), right.get(j));
            if ((descOrder && comparison > 0) || (!descOrder && comparison <= 0)) {
                data.set(k++, left.get(i++));
            } else {
                data.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            data.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            data.set(k++, right.get(j++));
        }
    }
}
