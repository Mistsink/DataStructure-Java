package MergeSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    private void MergeSort() {
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        _mergeSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void _mergeSort(T[] arr, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        _mergeSort(arr, l, mid);
        _mergeSort(arr, mid + 1, r);
        _merge(arr, l, mid, r);
    }

    private static <T extends Comparable<T>> void _merge(T[] arr, int l, int mid, int r) {
        T[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1; // 两部分的起始位置

        for (int k = l; k < r + 1; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[i - l].compareTo(temp[j - l]) < 0) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        // 为了避免在merge函数中每次都要重新开辟一个用以拷贝数组的临时空间
        // 故而在此一次创建出来
        T[] temp = Arrays.copyOf(arr, arr.length);

        _mergeSort2(arr, 0, arr.length - 1, temp);
    }

    // 优化
    private static <T extends Comparable<T>> void _mergeSort2(T[] arr, int l, int r, T[] temp) {

        // 当数据量较小时，改用插入排序会更快
//        if (r - l < 100) {
//            InsertionSort(arr, l, r);
//            return;
//        }

        if (l >= r) return;
        int mid = l + (r - l) / 2;
        _mergeSort2(arr, l, mid, temp);
        _mergeSort2(arr, mid + 1, r, temp);

        // 若已有序，则中间值有序，那就不需要merge
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            _merge2(arr, l, mid, r, temp);
        }
    }

    private static <T extends Comparable<T>> void _merge2(T[] arr, int l, int mid, int r, T[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int i = l, j = mid + 1; // 两部分的起始位置

        for (int k = l; k < r + 1; k++) {
            if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
            } else if (temp[i].compareTo(temp[j]) < 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }


    // 自底向上的归并排序
    public static <T extends Comparable<T>> void sortBU(T[] arr) {
        T[] temp = Arrays.copyOf(arr, arr.length);

        int n = arr.length;

        // sz表示每次合并的长度
        for (int sz = 1; sz < n; sz *= 2) {

            // 当数据量较小时，改用插入排序会更快
//        if (sz < 100) {
//            InsertionSort(arr, l, r);
//            continue;
//        }


            // 遍历两个区间的起始位置 i
            // [i, i + sz -1], [i + sz, i + sz + sz -1]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz -1].compareTo(arr[i + sz]) > 0) {
                    _merge2(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n -1), temp);
                }
            }

        }
    }

    private static int count;

    public static int solution(Integer[] arr) {
        count = 0;
        sort2(arr);
        return count;
    }

}