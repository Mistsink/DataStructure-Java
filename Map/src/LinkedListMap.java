public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V val;
        public Node next;
        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
        public Node(K key, V val) {
            this(key, val, null);
        }
        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return  (key.toString() + ":" + val.toString());
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key, boolean isGetPre) {
        Node cur = isGetPre ? dummyHead : dummyHead.next;

        while (cur != null) {
            if (!isGetPre && cur.key.equals(key)) {
                return cur; // isGetPre = false
            }
            if (isGetPre && cur.next != null && cur.next.key.equals(key)) {
                return cur; // isGetPre = true
            }
            cur = cur.next;
        }

        return null;
    }


    @Override
    public void add(K key, V val) {
        Node node = getNode(key, false);
        if (node == null) {
            size ++;
            dummyHead.next = new Node(key, val, dummyHead.next);
        } else {
            node.val = val;
        }
    }

    @Override
    public V remove(K key) {
        Node preNode = getNode(key, true);
        if (preNode == null) throw new IllegalArgumentException(preNode.key +  "does not exist");

        Node delNode = preNode.next;
        preNode.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.val;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key, false) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key, false);
        return node == null ? null: node.val;
    }

    @Override
    public void set(K key, V newVal) {
        Node node = getNode(key, false);
        if (node == null) throw new IllegalArgumentException(node.key +  "does not exist");

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

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Node cur = dummyHead.next;
        ret.append("linked list --map:\n");
        while (cur != null) {
            ret.append(cur.toString() + '\n');
            cur = cur.next;
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        LinkedListMap<Integer, String> map = new LinkedListMap<>();
        for (int i = 0; i < 10; i ++) {
            map.add(i, "key-" + i);
        }
        System.out.println(map);

        map.remove(3);
        System.out.println(map);
        map.add(2, "hhhh");
        System.out.println(map);

        System.out.println(map.getSize());
    }
}
