import QuickSort.QuickSort;
import ShellSort.ShellSort;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = { 1, 3, 4, 1, 7, 2 ,3, 1};
        printArr(arr);

        ShellSort.sort(arr);
        printArr(arr);

    }

    private static <T> void printArr(T[] arr) {
        System.out.println("print array:");
        for (T i :
                arr) {
            System.out.printf("%d\t", i);
        }
        System.out.println();
    }
}
