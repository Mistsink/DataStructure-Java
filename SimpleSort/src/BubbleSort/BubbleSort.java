package BubbleSort;

public class BubbleSort {
    private BubbleSort(){}

    /**
     * 优化点：
     * 1: 若有序，提前结束冒泡
     * 2: 根据最后一次swap的索引，跳过排好序的数组片段
     */
    public static <T extends Comparable<T>> void sort2(T[] arr) {
        for (int i = 0; i < arr.length - 1;) {

            // arr[n - i, n) 已排好序

            int lastSwapped = 0; // 记录最后一次swap的索引

            for (int j = 0; j < arr.length - i - 1; j ++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j+1);

                    lastSwapped = j + 1;
                }
            }

            i = arr.length - lastSwapped; // arr[n - i, n) 已排好序
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {

            for (int j = 0; j < arr.length - i - 1; j ++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) swap(arr, j, j+1);
            }
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
