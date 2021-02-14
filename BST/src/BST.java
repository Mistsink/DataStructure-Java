import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<T>> {
    private class Node{
        public T e;
        public Node left, right;

        public Node(T e) {
            this.e = e;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public void BST() {
        this.root = null;
        this.size = 0;
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

        if (node.e.compareTo(e) > 0) node.left = add(node.left, e);
        else node.right = add(node.right, e);

        return node;
    }

    public boolean contains(T e) {
        return contains(root, e);
    }

    private boolean contains(Node node, T e) {
        if (node == null) return false;

        if (node.e.compareTo(e) == 0) return true;
        else if (node.e.compareTo(e) > 0) return contains(node.left, e);
        else return contains(node.right, e);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            System.out.println(cur.e);

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
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            System.out.println(cur.e);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
    }


    public T minimum() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        return minimum(root).e;
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

        return cur.e;
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
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    public T maximum() {
        if (size == 0) throw new IllegalArgumentException("bst is empty");

        return maximum(root).e;
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

        return cur.e;
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

        if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else {
            // 左或右节点为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 左右节点均不为空
            // 将大于该节点的最小节点进行替换
            Node successor = minimum(node.right); // successor:后继节点
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
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

        ret.append(generateDepthString(depth) + node.e.toString() + "\n");

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
