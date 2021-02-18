public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        SegmentTree<Integer> st = new SegmentTree<>(arr, (x, y) -> x + y);

        System.out.println(st);
        System.out.println(st.query(1,2));
    }
}
