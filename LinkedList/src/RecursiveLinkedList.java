public class RecursiveLinkedList<T> implements LinkList<T> {
    private class Node {
        public T e;
        public RecursiveLinkedList.Node next;

        public Node(T e, RecursiveLinkedList.Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(T e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private RecursiveLinkedList.Node dummyHead;
    private int size;

    public RecursiveLinkedList() {
        dummyHead = new RecursiveLinkedList.Node();
        size = 0;
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
    public void add(int index, T e) {
//        Node pre = _getIndexPre(index, 0, dummyHead);
//        pre.next = new Node(e, pre.next);
        dummyHead.next = add(dummyHead.next, index, e);
        size++;
    }

    private Node add(Node node, int index, T e) {
        if (index == 0) return new Node(e, node);

        node.next = add(node.next, index - 1, e);
        return node;
    }


    @Override
    public void addFirst(T e) {
        add(0, e);
    }

    @Override
    public void addLast(T e) {
        add(size, e);
    }

    @Override
    public T get(int index) {
        return (T) _getIndexPre(index, 0, dummyHead.next).e;
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public void set(int index, T e) {
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("failed to add, index < 0 || index > size - 1");

        _getIndexPre(index, 0, dummyHead.next).e = e;
    }

    @Override
    public boolean contains(T e) {
        return _contains(e, dummyHead);
    }

    private boolean _contains(T e, Node list) {
        if (list == null) return false;

        if (list.e.equals(e)) return true;
        else return _contains(e, list.next);
    }

    @Override
    public T remove(int index) {
        Node pre = _getIndexPre(index, 0, dummyHead);
        Node cur = pre.next;
        if (cur == null) return null;
        pre.next = cur.next;
        cur.next = null;
        size--;
        return cur.e;
    }

    @Override
    public T removeFist() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("RecursiveLinkedList: head:");
        for (RecursiveLinkedList.Node cur = dummyHead.next; cur != null; cur = cur.next) {
            ret.append(cur.e);
            ret.append("->");
        }
        ret.append("NULL");
        return ret.toString();
    }

    private Node _getIndexPre(int index, int depth, Node pre) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("failed to operate, index error: index: " + index);

        if (depth == index) return pre;
        return _getIndexPre(index, depth + 1, pre.next);
    }
}
