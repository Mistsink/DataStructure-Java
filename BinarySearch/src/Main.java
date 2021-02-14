public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 4, 4, 5, 8, 19};
        printArr(arr);
        System.out.println("search:" + BinarySearch.search(arr, 8));
        System.out.println("upper:" + BinarySearch.upper(arr, 23 ));
        System.out.println("ceil:" + BinarySearch.ceil(arr, 7 ));
        System.out.println("lower_ceil:" + BinarySearch.lower_ceil(arr, 7 ));
    }

    public static void printArr(Integer[] arr) {
        for (Integer i: arr) {
            System.out.println("i: " + i);
        }
    }
}
