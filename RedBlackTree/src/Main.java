public class Main {
    public static void main(String[] args) {
        RBTree<Integer, Integer> rbTree = new RBTree();

        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int i = 0; i < nums.length; i++) {
            rbTree.add(i, nums[i]);
        }

        System.out.println("rbTree inOrder:");
        System.out.println(rbTree.contains(3));
        rbTree.inOrder();
        System.out.println();
//        rbTree.removeMin();
        System.out.println();
        rbTree.inOrder();
//        rbTree.removeMax();
        System.out.println();
        rbTree.inOrder();

//        System.out.println("bst1-in order:");
//        bst1.inOrder();
//        System.out.println("bst1-post order:");
//        bst1.postOrder();

    }
}
