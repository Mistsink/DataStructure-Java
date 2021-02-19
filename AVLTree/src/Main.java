public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> bst1 = new AVLTree<>();

        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums) {
            bst1.add(num);
        }

        bst1.inOrder();

        bst1.remove(2);
        bst1.remove(4);

        System.out.println("avl tree inorder:");
        bst1.inOrder();
        System.out.println("isAVL:" + bst1.isBalanced());
    }
}
