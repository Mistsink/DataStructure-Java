package InsertionSort;

import SelectionSort.SelectionSort;
import SelectionSort.Student;

public class InsertionSort {
    private InsertionSort() {}

    public static <T extends Comparable<T>> void sort(T[] arr) {

        for (int i = arr.length - 1; i >= 0; --i) {

            T t = arr[i];

            int j;

            for (j = i; j < arr.length - 1; ++j) {
                if (t.compareTo(arr[j+1]) > 0) {
                    arr[j] = arr[j+1];
                } else break;
            }

            arr[j] = t;

        }
    }

    public static <T> void swap(T[] arr, int i, int j) {
        if (i == j) return;
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
        InsertionSort.sort(students);
        for (Student student: students) {
            System.out.println(student);
        }

        Integer[] arr = {4, 2, 3, 7, 1};
        InsertionSort.sort(arr);
        for (Integer i: arr) {
            System.out.println(i);
        }
    }
}
