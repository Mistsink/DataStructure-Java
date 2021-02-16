package ShellSort;

public class ShellSort {
    private ShellSort(){}

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int h = arr.length / 2; // 递增值

        while (h >= 1) {
            for (int start = 0; start < h; start ++) {

                // 对arr[start, start+h, start + 2h ....]进行插入排序
                for (int i = start + h; i < arr.length; i += h) {
                    T t = arr[i];
                    int j;
                    for (j = i; j >= start + h && t.compareTo(arr[j - h]) < 0; j -= h)
                        arr[j] = arr[j - h];
                    arr[j] = t;
                }
            }


            h /= 2;
        }
    }
}
