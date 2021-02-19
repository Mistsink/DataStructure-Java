public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;

            left = right = null;

            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        size = 0;
    }


    public int getSize() {return size;}
    public boolean isEmpty() {return size == 0;}


    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
    }


    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.color = node.color; // 维持根节点的color
        node.color = RED;           // 将往左下移的原根节点color设为red，后续进行颜色维护

        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        newRoot.color = node.color; // 维持根节点的color
        node.color = RED;           // 将往右下移的原根节点color设为red，后续进行颜色维护

        return newRoot;
    }


    private void flipColors(Node node) {
        node.color = RED;
        node.right.color = node.left.color = BLACK;
    }


    public void add(K key, V val) {
        root = add(root, key, val);
        root.color = BLACK;     // 保持根节点color为black
    }
    private Node add(Node node, K key, V val) {
        if (node == null) {
            size ++;
            return new Node(key, val);
        }

        if (node.key.compareTo(key) > 0) node.left = add(node.left, key, val);
        else node.right = add(node.right, key, val);


        // !!! 黑平衡的维护
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }






    public boolean contains(K e) {
        return contains(root, e);
    }

    private boolean contains(Node node, K e) {
        if (node == null) return false;

        if (node.key.compareTo(e) == 0) return true;
        else if (node.key.compareTo(e) > 0) return contains(node.left, e);
        else return contains(node.right, e);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.key + "--->" + node.value);
        inOrder(node.right);
    }
}
