public class Main {
    public static void main(String[] args) {
        LoopQueue<Integer> arr = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            arr.enqueue(i);
        }

        arr.enqueue(11);

        System.out.println(arr);
        System.out.println(arr.dequeue());
        System.out.println(arr);

    }
}
