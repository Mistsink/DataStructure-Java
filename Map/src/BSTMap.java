public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node{
        public K key;
        public V val;
        public Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }


    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) < 0 ) {
            return getNode(node.right, key);
        } else {
            return getNode(node.left, key);
        }
    }


    @Override
    public void add(K key, V val) {
        root = add(root, key, val);
    }
    private Node add(Node node, K key, V val) {
        if (node == null) {
            size ++;
            return new Node(key, val);
        }

        if (node.key.compareTo(key) == 0) {
            node.val = val;
        } else if (node.key.compareTo(key) > 0)
            node.left = add(node.left, key, val);
        else node.right = add(node.right, key, val);

        return node;
    }


    @Override
    public V remove(K key) {
        Node delNode = getNode(root, key);
        if (delNode != null) {
            root = remove(root, key);
            return delNode.val;
        }

        return null;
    }
    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key.compareTo(node.key) == 0

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

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }
    private Node minimum(Node node) {
        return node.left == null ? node : minimum(node.left);
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


    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.val;
    }


    @Override
    public void set(K key, V newVal) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(node.key + "dose not exist");
        }
        node.val = newVal;
    }


    @Override
    public int getSize() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap<>();
        for (int i = 0; i < 10; i ++) {
            map.add(i, "key-" + i);
        }
        map.inOrder();

        map.remove(3);
        map.inOrder();
        map.add(2, "hhhh");
        map.inOrder();

        System.out.println(map.getSize());
    }
}
