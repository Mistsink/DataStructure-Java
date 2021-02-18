import java.util.TreeMap;

/**
 * 677. 键值映射
 */

class MapSum {

    private class Node {
        public int value;
        public TreeMap<Character, Node> next;

        public Node(int val) {
            this.value = val;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;


    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i ++) {
            char c = key.charAt(i);

            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            cur = cur.next.get(c);

            if (cur == null) return 0;
        }

        return sum(cur);
    }
    private int sum(Node node) {
        int res = node.value;
        for (char c :
                node.next.keySet()) {
            res += sum(node.next.get(c));
        }

        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */