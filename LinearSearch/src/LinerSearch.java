public class LinerSearch {
    private LinerSearch() {}

    public static <E> int search(E[] data, E tar) {
        for (int i = 0; i < data.length; ++i) {
            if (data[i].equals(tar)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {(int) 1e7, (int) 1e8};
        for (int n: arr) {
            Integer[] testArr = ArrayGenerator.generateOrderedArray(n);
            long startTime = System.nanoTime();
            LinerSearch.search(testArr, n);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println(n + "times, run time:" + time + 's');
        }
    }
}
