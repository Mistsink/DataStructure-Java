public class HeapSort {
    private HeapSort(){}

    public static <T extends Comparable<T>> void sort(T[] arr) {
        MaxHeap<T> maxHeap = new MaxHeap<>();
        for (T e : arr) {
            maxHeap.add(e);
        }
        for (int i = arr.length -1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        if (arr.length <= 1) return;

        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            siftDown(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i --) {
            swap(arr, 0, i);
            siftDown(arr, 0, i);
        }
    }

    // 确保arr[ 0, length ) 为最大堆，并对 索引 i 进行siftDown
    private static <T extends Comparable<T>> void siftDown(T[] arr, int i, int length) {
        if (length <= 0) return;

        int left = 2 * i + 1, right = left + 1;

        while (left < length){
            int maxChild = left;

            if (right < length) {
                maxChild = arr[left].compareTo(arr[right]) > 0 ? left: right;
            }

            if (arr[i].compareTo(arr[maxChild]) > 0) break;

            swap(arr, i ,maxChild);

            i = maxChild;
            left = 2 * i + 1; right = left + 1;
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
