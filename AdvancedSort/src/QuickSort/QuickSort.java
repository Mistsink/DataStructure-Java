package QuickSort;

import java.util.Random;

public class QuickSort {
    private static Random random;
    private void QuickSort(){}

    public static <T extends Comparable<T>> void QuickSort(T[] arr) {
        random = new Random();
        QuickSort(arr, 0, arr.length-1);
    }

    private static <T extends Comparable<T>> void QuickSort(T[] arr, int l, int r) {
        if (l >= r) return;

        int p = partition(arr, l, r);
        QuickSort(arr, l, p - 1);
        QuickSort(arr, p + 1, r);
    }

    /**
     * 返回基轴元素在数组中的下标
     * @param arr T[]
     * @param l left index : int
     * @param r right index : int
     * @return
     */
    private static <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        // 随机选取数组中的基准值--->为了防止出现有序数组栈溢出这种情况
        // 生成[l, r]之间的随记索引值
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, p, l);

        int j = l;

        // arr[l + 1, j] < val, arr[j + 1, r] >= val
        for (int i = l + 1; i <= r; i ++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j ++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static <T extends Comparable<T>> void QuickSort2Ways(T[] arr){
        random = new Random();
        QuickSort2Ways(arr, 0, arr.length -1);
    }

    /**
     * 双路快排（解决第一版快排中遇到全等数组时性能退化的问题）
     * @param arr
     * @param l left index: int
     * @param r right index: int
     */
    private static <T extends Comparable<T>> void QuickSort2Ways(T[] arr, int l, int r) {
        if (l >= r) return;

        int p = partition2(arr, l, r);
        QuickSort2Ways(arr, l, p - 1);
        QuickSort2Ways(arr, p + 1, r);
    }

    /**
     * 返回基轴元素在数组中的下标
     * @param arr T[]
     * @param l left index : int
     * @param r right index : int
     * @return
     */
    private static <T extends Comparable<T>> int partition2(T[] arr, int l, int r) {
        // 随机选取数组中的基准值--->为了防止出现有序数组栈溢出这种情况
        // 生成[l, r]之间的随记索引值
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, p, l);

        int i = l + 1, j = r;

        while (i < j) {
            // arr[l + 1, i - 1] <= val, arr[j + 1, r] >= val
            while (i <= j && arr[i].compareTo(arr[l]) < 0) i ++;
            while (i <= j && arr[j].compareTo(arr[l]) > 0) j --;

            if (i >= j) break;

            swap(arr, i, j);
            i ++;
            j --;
        }

        swap(arr, l, j);
        return j;
    }

    public static <T extends Comparable<T>> void QuickSort3Ways(T[] arr) {
        random = new Random();
        QuickSort3Ways(arr, 0, arr.length -1);
    }

    /**
     * 三路快排
     * （当数组中有很多等值元素时，双路快排仍会继续处理等值元素，
     * 该种算法会更优，仅仅区分出等值元素，而不会在下一轮快排中继续处理）
     * @param arr
     * @param l left index: int
     * @param r right index: int
     */
    private static <T extends Comparable<T>> void QuickSort3Ways(T[] arr, int l, int r) {
        if (l >= r) return;
        int p = random.nextInt(r - l + 1) + l;
        swap(arr, p, l);

        // arr[l + 1, lessIndex] < val
        // arr[lessIndex + 1, i] == val
        // arr[greaterIndex, r] > val
        int lessIndex = l, i = l + 1, greaterIndex = r + 1;
        while (i < greaterIndex) {
            if (arr[i].compareTo(arr[l]) < 0) {
                lessIndex ++;
                swap(arr, lessIndex, i);
                i ++;
            } else if (arr[i].compareTo(arr[l]) > 0) {
                greaterIndex --;
                swap(arr, greaterIndex, i);
            } else {
                i ++;
            }
        }
        // arr[l, lessIndex - 1] < val
        // arr[lessIndex, greaterIndex -1] == val
        // arr[greaterIndex, r] > val
        swap(arr, l, lessIndex);
        QuickSort3Ways(arr, l, lessIndex - 1);
        QuickSort3Ways(arr, greaterIndex, r);
    }
}
