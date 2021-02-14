public class Main {
    public static void main(String[] args) {
        BST<Integer> bst1 = new BST<>();

        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums) {
            bst1.add(num);
        }

        System.out.println("bst1-pre order:");
        bst1.inOrder();
        System.out.println();
        bst1.removeMin();
        System.out.println();
        bst1.inOrder();
        bst1.removeMax();
        System.out.println();
        bst1.inOrder();

//        System.out.println("bst1-in order:");
//        bst1.inOrder();
//        System.out.println("bst1-post order:");
//        bst1.postOrder();

    }
}
