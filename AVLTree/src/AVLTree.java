import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> {
    private class Node{
        public T key;
        public Node left, right;
        public int height;

        public Node(T e) {
            this.height = 1;
            this.key = e;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public void BST() {
        this.root = null;
        this.size = 0;
    }


    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }


    private int getBalanceFactor(Node node) {
        if (node == null) return 0;

        return getHeight(node.left) - getHeight(node.right);
    }


    private Node rightRotate(Node node) {
        Node newRoot = node.left, newLeft = newRoot.right;

        newRoot.right = node;
        node.left = newLeft;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }
    private Node leftRotate(Node node) {
        Node newRoot = node.right, newRight = newRoot.left;

        newRoot.left = node;
        node.right = newRight;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }


    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}


    public void add(T e) {
        root = add(root, e);
    }
    private Node add(Node node, T e) {
        if (node == null) {
            size ++;
            return new Node(e);
        }

        if (node.key.compareTo(e) > 0) node.left = add(node.left, e);
        else node.right = add(node.right, e);

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        node = adjustBalance(node);

        return node;
    }


    private Node adjustBalance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // 右旋: LL
        // 左 > 右，且左子树中，左 > 右
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            node = rightRotate(node);
        }
        // 左旋: RR
        // 左 < 右，且右子树中，左 < 右
        else if (balanceFactor < -1 && getBalanceFactor(node.left) <= 0) {
            node = leftRotate(node);
        }
        // LR
        else if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }
        // RL
        else if (balanceFactor < -1 && getBalanceFactor(node.left) > 0) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }

        return node;
    }


    public boolean isBST() {
        ArrayList<T> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) return false;
        }
        return true;
    }


    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if (node == null) return true;

        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) > 1) return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }


    public boolean contains(T e) {
        return contains(root, e);
    }
    private boolean contains(Node node, T e) {
        if (node == null) return false;

        if (node.key.compareTo(e) == 0) return true;
        else if (node.key.compareTo(e) > 0) return contains(node.left, e);
        else return contains(node.right, e);
    }


    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            System.out.println(cur.key);

            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);

        }
    }


    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }
    private void inOrder(Node node, ArrayList<T> keys) {
        if (node == null) return;

        inOrder(node.left);
        keys.add(node.key);
        inOrder(node.right);
    }

    
    public T minimum() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        return minimum(root).key;
    }
    private Node minimum(Node node) {
        return node.left == null ? node : minimum(node.left);
    }
    public T minimumNR() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }

        return cur.key;
    }


    public T removeMin() {
        T min = minimum();
        root = removeMin(root);
        return min;
    }
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            size --;
            node.right = null;
            return adjustBalance(rightNode);
        }
        node.left = removeMin(node.left);
        return adjustBalance(node);
    }


    public T maximum() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        return maximum(root).key;
    }
    private Node maximum(Node node) {
        return node.right == null ? node : maximum(node.right);
    }
    public T maximumNR() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }

        return cur.key;
    }


    public T removeMax() {
        T max = maximum();
        root = removeMax(root);
        return max;
    }
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            size --;
            node.left = null;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    public void remove(T e) {
        root = remove(root, e);
    }
    private Node remove(Node node, T e) {
        if (node == null) return null;

        Node retNode = null;

        if (node.key.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else if (node.key.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else {
            // 左或右节点为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                // 左右节点均不为空
                // 将大于该节点的最小节点进行替换
                Node successor = minimum(node.right); // successor:后继节点
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        retNode = adjustBalance(retNode);

        return retNode;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        generateBSTString(root, 0, ret);
        return ret.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder ret) {
        if (node == null) {
            ret.append(generateDepthString(depth) + "null\n");
            return;
        }

        ret.append(generateDepthString(depth) + node.key.toString() + "\n");

        generateBSTString(node.left, depth + 1, ret);
        generateBSTString(node.right, depth + 1, ret);

    }

    private String generateDepthString(int depth) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            ret.append("--");
        }
        return ret.toString();
    }
}
