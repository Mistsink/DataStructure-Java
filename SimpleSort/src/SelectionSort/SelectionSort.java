package SelectionSort;

public class SelectionSort {
    private SelectionSort() {}

    public static <T extends Comparable<T>> void sort(T[] arr) {

        for (int i = arr.length - 1; i >= 0; --i) {

            int maxIndex = i;

            for (int j = i; j >= 0; --j) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            swap(arr, i, maxIndex);
        }
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Student[] students = {
                new Student("a", 98),
                new Student("b", 78),
                new Student("c", 100),
        };
        SelectionSort.sort(students);
        for (Student student: students) {
            System.out.println(student);
        }

        Integer[] arr = {4, 2, 3, 7, 1};
        SelectionSort.sort(arr);
        for (Integer i: arr) {
            System.out.println(i);
        }
    }
}
