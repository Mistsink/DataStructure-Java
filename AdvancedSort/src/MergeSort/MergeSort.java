package MergeSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    private void MergeSort(){}

    public static <T extends Comparable<T>> void sort(T[] arr) {
        _mergeSort(arr, 0, arr.length-1);
    }

    private static <T extends Comparable<T>> void _mergeSort(T[] arr, int l, int r) {
        if (l >= r) return;
        int mid  = l + (r - l) / 2;
        _mergeSort(arr, l, mid);
        _mergeSort(arr, mid + 1, r);
        _merge(arr, l, mid, r);
    }

    private static <T extends Comparable<T>> void _merge(T[] arr, int l, int mid, int r) {
        T[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1; // 两部分的起始位置

        for (int k = l; k < r + 1; k++){
            if (i > mid){
                arr[k] = temp[j - l]; j++;
            } else if (j > r) {
                arr[k] = temp[i - l]; i++;
            } else if (temp[i - l].compareTo(temp[j - l]) < 0) {
                arr[k] = temp[i - l]; i++;
            } else {
                arr[k] = temp[j - l]; j++;
            }
        }


    }

}
