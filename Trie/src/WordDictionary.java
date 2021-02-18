import java.util.TreeMap;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 */
class WordDictionary {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        if (!cur.isWord) cur.isWord = true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) return node.isWord;

        char c = word.charAt(index);

        if (c != '.') {
            Node cur = node.next.get(c);
            if (cur == null) return false;
            return match(cur, word, index + 1);
        } else {
            for (char keyChar :
                    node.next.keySet()) {
                if (match(node.next.get(keyChar), word, index + 1))
                    return true;
            }
            return false;
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
