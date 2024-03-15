package utils;

import generic.DataStructures.List;
import shared.NetProjectFile;
import shared.ProjectFile;

public class QuickSort {
    public static void sort(List<NetProjectFile> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            sort(list, low, pi - 1);
            sort(list, pi + 1, high);
        }
    }

    private static int partition(List<NetProjectFile> list, int low, int high) {
        String pivot = list.get(high).getName();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getName().compareTo(pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<NetProjectFile> list, int i, int j) {
        NetProjectFile temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}