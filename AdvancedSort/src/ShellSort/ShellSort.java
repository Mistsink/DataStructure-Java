package ShellSort;

public class ShellSort {
    private ShellSort(){}

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int h = arr.length / 2; // 递增值

        while (h >= 1) {
            // 对arr[h, n)进行插入排序
            for (int i = h; i < arr.length; i ++) {
                T t = arr[i];
                int j;
                for (j = i; j >= h && t.compareTo(arr[j - h]) < 0; j -= h)
                    arr[j] = arr[j - h];
                arr[j] = t;
            }


            h /= 2;
        }
    }

    public static <T extends Comparable<T>> void sort2(T[] arr) {
        int h = 1; // 递增值

        while (h < arr.length / 3) h = h * 3 + 1; // 步长序列

        while (h >= 1) {
            // 对arr[h, n)进行插入排序
            for (int i = h; i < arr.length; i ++) {
                T t = arr[i];
                int j;
                for (j = i; j >= h && t.compareTo(arr[j - h]) < 0; j -= h)
                    arr[j] = arr[j - h];
                arr[j] = t;
            }


            h /= 3;
        }
    }
}
