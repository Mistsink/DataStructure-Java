import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i ++){
            maxHeap.add(random.nextInt(n));
            arr[i] = random.nextInt(n);
        }


        for (int i = 0; i < n; i ++){
//            arr[i] = maxHeap.extractMax();
            System.out.println("i "+ i  + "    " + arr[i]);
        }

        HeapSort.sort(arr);
        System.out.println("sort ");
        for (int i = 0; i < n; i ++){
            System.out.println("i "+ i  + "    " + arr[i]);
        }

//        for (int i = 1; i<n;i++) {
//            if (arr[i -1] < arr[i]) {
//                System.out.println("error");
//                System.out.println("i:" + i + ": " + arr[i - 1] + "-----" + arr[i]);
//            }
//        }
    }
}
